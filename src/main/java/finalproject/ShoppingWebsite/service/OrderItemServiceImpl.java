package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.OrderItemResponse;
import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.model.OrderItem;
import finalproject.ShoppingWebsite.repository.ItemRepository;
import finalproject.ShoppingWebsite.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderItemServiceImpl implements OrderItemService{

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    ItemRepository itemRepository;


    @Override
    public OrderItemResponse createOrderItem(OrderItem orderItem) {
        orderItemRepository.createOrderItem(orderItem);
        Item item = itemRepository.getItemById(orderItem.getItemId());

        OrderItemResponse orderItemResponse = new OrderItemResponse();
        orderItemResponse.setOrderId(orderItem.getOrderId());
        orderItemResponse.setQuantity(orderItem.getQuantity());

        return orderItemResponse;
    }

    @Override
    public void updateOrderItem(OrderItem orderItem){
        orderItemRepository.updateOrderItem(orderItem);
    }

    @Override
    public void deleteOrderItemById(Long orderItemId){
        orderItemRepository.deleteOrderItemById(orderItemId);
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId){
        return orderItemRepository.getOrderItemById(orderItemId);
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.getAllOrderItemsByOrderId(orderId);
        return  orderItems;
    }



}
