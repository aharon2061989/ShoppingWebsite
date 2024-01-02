package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.Order;

import java.util.List;

public interface OrderRepository {
    void createOrder(Order order);
    void updateOrder(Order order);
    Order findActiveOrderByUserId(Long userId);
    void deleteOrder(Long orderId);
    Order getOrderByOrderId(Long orderId);
    List<Order> getAllOrdersByUserId(Long userId);

    Order placeOrder(Order order);
}
