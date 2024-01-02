package finalproject.ShoppingWebsite.dto;

import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.model.Order;
import finalproject.ShoppingWebsite.model.OrderItem;


import java.util.List;

public class OrderResponse {
    private Order order;
    private List<OrderItemResponse> orderItems;


    public OrderResponse() {
    }

    public OrderResponse(Order order, List<OrderItemResponse> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemResponse> orderItems) {
        this.orderItems = orderItems;
    }
}


