package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.OrderItem;

import java.util.List;

public interface OrderItemRepository {
    void createOrderItem(OrderItem orderItem);
    void updateOrderItem(OrderItem orderItem);
    void deleteOrderItemById(Long orderItemId);
    OrderItem getOrderItemById(Long orderItemId);
    List<OrderItem> getAllOrderItemsByOrderId(Long orderId);
}
