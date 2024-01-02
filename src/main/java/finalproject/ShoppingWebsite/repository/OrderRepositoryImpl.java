package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.Order;
import finalproject.ShoppingWebsite.model.enums.OrderStatus;
import finalproject.ShoppingWebsite.repository.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    private static final String ORDER_DETAILS_TABLE_NAME = "order_details";

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public void createOrder(Order order) {
        String sql = "INSERT INTO " + ORDER_DETAILS_TABLE_NAME + "(order_user_id, order_date, shipping_city, shipping_country, shipping_address, total_price, order_status) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                order.getOrderUserId(),
                order.getOrderDate(),
                order.getShippingCity(),
                order.getShippingCountry(),
                order.getShippingAddress(),
                order.getTotalPrice(),
                order.getOrderStatus().name()
        );
        Long orderId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
        order.setOrderId(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE " + ORDER_DETAILS_TABLE_NAME + " SET order_user_id=?, order_date=?, shipping_city=?, shipping_country=?, shipping_address=?, total_price=?, order_status=? WHERE order_id=?";
        jdbcTemplate.update(
                sql,
                order.getOrderUserId(),
                order.getOrderDate(),
                order.getShippingCity(),
                order.getShippingCountry(),
                order.getShippingAddress(),
                order.getTotalPrice(),
                order.getOrderStatus().name()
        );
    }

    @Override
    public void deleteOrder(Long orderId) {
        String sql = "DELETE FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE order_id=?";
        jdbcTemplate.update(sql, orderId);
    }

    @Override
    public Order findActiveOrderByUserId(Long userId) {
        try {
            String sql = "SELECT * FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE order_user_id=? AND order_status='TEMP'";
            return jdbcTemplate.queryForObject(sql, orderMapper,userId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Order getOrderByOrderId(Long orderId) {
        String sql = "SELECT * FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE order_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, orderMapper, orderId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }


    @Override
    public List<Order> getAllOrdersByUserId(Long userId) {
        String sql = "SELECT * FROM " + ORDER_DETAILS_TABLE_NAME + " WHERE order_user_id=?";
        try {
            return jdbcTemplate.query(sql, orderMapper, userId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public Order placeOrder(Order order){
        String sql = "UPDATE " + ORDER_DETAILS_TABLE_NAME + " SET order_status=? WHERE order_id=?";
        jdbcTemplate.update(sql, OrderStatus.CLOSE.name(), order.getOrderId());
        return order;
    }

}
