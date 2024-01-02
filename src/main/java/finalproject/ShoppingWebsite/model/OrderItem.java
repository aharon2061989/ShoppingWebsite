package finalproject.ShoppingWebsite.model;

import finalproject.ShoppingWebsite.dto.ItemResponse;
import finalproject.ShoppingWebsite.dto.OrderItemResponse;

public class OrderItem {
    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private int quantity;


    public OrderItem() {
    }

    public OrderItem(Long orderItemId, Long orderId, Long itemId, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;

    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Long getOrderItemId() {
        return orderItemId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getItemId() {
        return itemId;
    }
    public int getQuantity() {
        return quantity;
    }
    public OrderItemResponse toOrderItemResponse(OrderItem orderItem, ItemResponse itemResponse){
        return new OrderItemResponse(
                orderItem.getOrderId(),
                orderItem.getOrderItemId(),
                itemResponse,
                orderItem.getQuantity()
        );
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
