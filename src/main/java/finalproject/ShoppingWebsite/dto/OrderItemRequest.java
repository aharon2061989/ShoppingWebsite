package finalproject.ShoppingWebsite.dto;

import finalproject.ShoppingWebsite.model.OrderItem;

public class OrderItemRequest {
    private Long userId;
    private Long itemId;
    private int quantity;

    public OrderItemRequest() {
    }

    public OrderItemRequest(Long userId, Long itemId, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
