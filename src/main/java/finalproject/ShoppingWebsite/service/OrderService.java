package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.OrderItemRequest;
import finalproject.ShoppingWebsite.dto.OrderResponse;
import finalproject.ShoppingWebsite.model.*;

import java.util.List;

public interface OrderService {
    void createOrder(Order order);
    void updateOrder(Order order);
    Order findActiveOrderByUserId(Long userId);
    void deleteOrder(Long orderId);

    void checkStockAvailability(OrderItemRequest orderItemRequest);

    OrderResponse addItemToOrder(OrderItemRequest orderItemRequest);

    OrderResponse removeItemFromOrder(OrderItemRequest orderItemRequest);

    Order getOrderById(Long orderId);

    List<Order> getAllOrdersByUserId(Long userId);
    List<OrderResponse> getListOfAllFullOrders(Long userId);

    OrderResponse placeOrder(Order order);
}
