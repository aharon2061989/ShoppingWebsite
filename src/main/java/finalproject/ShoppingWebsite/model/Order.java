package finalproject.ShoppingWebsite.model;

import finalproject.ShoppingWebsite.dto.OrderItemResponse;
import finalproject.ShoppingWebsite.dto.OrderResponse;
import finalproject.ShoppingWebsite.model.enums.OrderStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.*;

public class Order {
    private Long orderId;
    private Long orderUserId;
    private Date orderDate;
    private String shippingCity;
    private String shippingCountry;
    private String shippingAddress;
    private Double totalPrice;
    private OrderStatus orderStatus;

    public Order() {
    }

    public String getShippingCity() {

        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }

    public Order(Long orderId, Long orderUserId, Date orderDate, String shippingCity, String shippingCountry, String shippingAddress, Double totalPrice, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderUserId = orderUserId;
        this.orderDate = orderDate;
        this.shippingCity = shippingCity;
        this.shippingCountry = shippingCountry;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Long orderUserId) {
        this.orderUserId = orderUserId;
    }
    public OrderResponse toOrderResponse(Order order, List<OrderItemResponse> orderItems){
        return new OrderResponse(
                order,
                orderItems
        );
    }
}

