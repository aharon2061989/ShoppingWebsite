package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.OrderItemResponse;
import finalproject.ShoppingWebsite.model.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItemResponse createOrderItem(OrderItem orderItem);

    void updateOrderItem(OrderItem orderItem);

    void deleteOrderItemById(Long orderItemId);

    OrderItem getOrderItemById(Long orderItemId);

    List<OrderItem> getAllOrderItemsByOrderId(Long orderId);
}
