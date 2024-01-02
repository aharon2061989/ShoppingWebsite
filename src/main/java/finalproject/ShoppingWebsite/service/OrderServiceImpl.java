package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.ItemResponse;
import finalproject.ShoppingWebsite.dto.OrderItemRequest;
import finalproject.ShoppingWebsite.dto.OrderItemResponse;
import finalproject.ShoppingWebsite.dto.OrderResponse;
import finalproject.ShoppingWebsite.model.*;
import finalproject.ShoppingWebsite.model.enums.OrderStatus;
import finalproject.ShoppingWebsite.repository.CustomUserRepository;
import finalproject.ShoppingWebsite.repository.ItemRepository;
import finalproject.ShoppingWebsite.repository.OrderItemRepository;
import finalproject.ShoppingWebsite.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomUserRepository customUserRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public void createOrder(Order order) {
        orderRepository.createOrder(order);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteOrder(orderId);
    }

    @Override
    public Order findActiveOrderByUserId(Long userId) {
        Order order = orderRepository.findActiveOrderByUserId(userId);
        if (order == null) {
            return null;
        }
        return order;
    }

    @Override
    public void checkStockAvailability(OrderItemRequest orderItemRequest) {
        try {
            Long itemId = orderItemRequest.getItemId();
            int requestedQuantity = orderItemRequest.getQuantity();
            int availableStock = itemRepository.getStockQuantity(itemId);

            if (availableStock < requestedQuantity) {
                throw new IllegalStateException("Insufficient stock quantity for the requested item");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("Stock information not found for the item", e);
        }
    }



    @Override
    public OrderResponse addItemToOrder(OrderItemRequest orderItemRequest) {
        Long userId = orderItemRequest.getUserId();
        Long itemId = orderItemRequest.getItemId();
        int quantity = orderItemRequest.getQuantity();
        CustomUser user = customUserRepository.getUserById(userId);

        Order order = findActiveOrderByUserId(userId);
        if (order == null) {
            order = new Order();
            order.setOrderUserId(userId);
            order.setOrderDate(new Date());
            order.setShippingCity(user.getCity());
            order.setShippingCountry(user.getCountry());
            order.setShippingAddress(user.getAddress());
            order.setTotalPrice(0.00);
            order.setOrderStatus(OrderStatus.TEMP);
            orderRepository.createOrder(order);
            order.setOrderId(order.getOrderId());
        }
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(order);
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        orderResponse.setOrderItems(orderItemResponses);
        List<OrderItem> orderItems = orderItemRepository.getAllOrderItemsByOrderId(order.getOrderId());
        boolean itemExistsInOrder = false;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getItemId().equals(itemId)) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                orderItemRepository.updateOrderItem(orderItem);
                itemExistsInOrder = true;
                break;
            }
        }
        for (OrderItem orderItem : orderItems){
            ItemResponse itemResponse = itemRepository.getItemById(orderItem.getItemId()).toItemResponse();
            OrderItemResponse orderItemResponse = orderItem.toOrderItemResponse(orderItem, itemResponse);
            orderItemResponses.add(orderItemResponse);
        }

        if (!itemExistsInOrder){
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setOrderId(order.getOrderId());
            newOrderItem.setItemId(itemId);
            newOrderItem.setQuantity(quantity);
            orderItemRepository.createOrderItem(newOrderItem);
            newOrderItem.setOrderId(newOrderItem.getOrderId());
            System.out.println("this is the orderitemId:" + newOrderItem.getOrderItemId());
            Long orderItemId = newOrderItem.getOrderItemId();

            ItemResponse itemResponse = itemRepository.getItemById(newOrderItem.getItemId()).toItemResponse();
            OrderItemResponse newOrderItemResponse = new OrderItemResponse(newOrderItem.getOrderId(), orderItemId, itemResponse, newOrderItem.getQuantity());;
            orderResponse.getOrderItems().add(newOrderItemResponse);
        }

        orderResponse.setOrderItems(orderItemResponses);
        return orderResponse;
    };


    @Override
    public OrderResponse removeItemFromOrder(OrderItemRequest orderItemRequest){
        Long userId = orderItemRequest.getUserId();
        Long itemId = orderItemRequest.getItemId();
        int quantity = orderItemRequest.getQuantity();
        Order order = findActiveOrderByUserId(userId);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrder(order);
        if (order == null){
            throw new EntityNotFoundException("Order not found for user with ID: " + userId);

        }
        List<OrderItem> orderItems = orderItemRepository.getAllOrderItemsByOrderId(order.getOrderId());
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        List<OrderItem> itemsToRemove = new ArrayList<>();

        for (OrderItem orderItem : orderItems){
            if (orderItem.getItemId().equals(itemId)){
                orderItem.setQuantity(Math.max(0, orderItem.getQuantity() - quantity));
                orderItemRepository.updateOrderItem(orderItem);
                if (orderItem.getQuantity() == 0) {
                    itemsToRemove.add(orderItem);
                    orderItemRepository.deleteOrderItemById(orderItem.getOrderItemId());
                }
            }
        }
        orderItems.removeAll(itemsToRemove);


        for (OrderItem orderItem : orderItems){
            ItemResponse itemResponse = itemRepository.getItemById(orderItem.getItemId()).toItemResponse();
            OrderItemResponse orderItemResponse = orderItem.toOrderItemResponse(orderItem, itemResponse);
            orderItemResponses.add(orderItemResponse);
        }
        orderResponse.setOrderItems(orderItemResponses);
        return orderResponse;
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderByOrderId(orderId);
    }

    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        List<Order> userOrders = orderRepository.getAllOrdersByUserId(userId);
        return userOrders;
    }

    @Override
    public List<OrderResponse> getListOfAllFullOrders(Long userId) {
        List<Order> orders = orderRepository.getAllOrdersByUserId(userId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders){
            List<OrderItem> orderItems = orderItemRepository.getAllOrderItemsByOrderId(order.getOrderId());
            List<OrderItemResponse> orderItemResponses = new ArrayList<>();

            for (OrderItem orderItem : orderItems) {
                ItemResponse itemResponse = itemRepository.getItemById(orderItem.getItemId()).toItemResponse();
                OrderItemResponse orderItemResponse = orderItem.toOrderItemResponse(orderItem, itemResponse);
                orderItemResponses.add(orderItemResponse);
            }
            OrderResponse orderResponse = order.toOrderResponse(order,orderItemResponses);
            orderResponses.add(orderResponse);
        }
        return orderResponses;
    }

    @Override
    public OrderResponse placeOrder(Order order){
        List<OrderItem> orderItems = orderItemRepository.getAllOrderItemsByOrderId(order.getOrderId());
        List<OrderItemResponse> orderItemResponses = new ArrayList<>();
        OrderResponse orderResponse = new OrderResponse();

        for (OrderItem orderItem : orderItems){
            Item item = itemRepository.getItemById(orderItem.getItemId());
            int quantityInOrder = orderItem.getQuantity();

            if (item.getStockQuantity() < quantityInOrder){
                throw new RuntimeException("Insufficient stock for item: " + item.getItemId());
            }
            int newStockQuantity = item.getStockQuantity() - quantityInOrder;
            itemRepository.updateItemStockQuantity(item, newStockQuantity);
            ItemResponse itemResponse = item.toItemResponse();
            OrderItemResponse orderItemResponse = orderItem.toOrderItemResponse(orderItem, itemResponse);
            orderItemResponses.add(orderItemResponse);
            System.out.println("Item ID: " + item.getItemId() + ", Stock Quantity after update: " + newStockQuantity);
        }

        order.setOrderStatus(OrderStatus.CLOSE);
        orderRepository.placeOrder(order);
        orderResponse.setOrder(order);
        orderResponse.setOrderItems(orderItemResponses);
        return orderResponse;
    }
}
