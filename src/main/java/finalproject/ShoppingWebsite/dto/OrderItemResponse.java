package finalproject.ShoppingWebsite.dto;

public class OrderItemResponse {
    private Long orderId;
    private Long orderItemId;
    private ItemResponse itemResponse;
    private int quantity;

    public OrderItemResponse() {
    }

    public OrderItemResponse(Long orderId, Long orderItemId, ItemResponse itemResponse, int quantity) {
        this.orderId = orderId;
        this.orderItemId = orderItemId;
        this.itemResponse = itemResponse;
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ItemResponse getItemResponse() {
        return itemResponse;
    }

    public void setItemResponse(ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }
}
