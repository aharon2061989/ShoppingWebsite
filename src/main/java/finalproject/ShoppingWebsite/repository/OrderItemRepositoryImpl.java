package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.OrderItem;
import finalproject.ShoppingWebsite.repository.mapper.OrderItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    OrderItemMapper orderItemMapper;

    private static final String ORDER_ITEMS_TABLE_NAME = "order_item";
    @Override
    public void createOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO " + ORDER_ITEMS_TABLE_NAME + "(order_id, item_id, quantity) values (?, ?, ?)";
        jdbcTemplate.update(
                sql,
                orderItem.getOrderId(),
                orderItem.getItemId(),
                orderItem.getQuantity()
        );
        Long orderItemId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
        orderItem.setOrderItemId(orderItemId);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        String sql = "UPDATE " + ORDER_ITEMS_TABLE_NAME + " SET order_id=?, item_id=?, quantity=? WHERE order_item_id=?";
        jdbcTemplate.update(
                sql,
                orderItem.getOrderId(),
                orderItem.getItemId(),
                orderItem.getQuantity(),
                orderItem.getOrderItemId()
        );
    }

    @Override
    public void deleteOrderItemById(Long orderItemId) {
        String sql = "DELETE FROM " + ORDER_ITEMS_TABLE_NAME + " WHERE order_item_id=?";
        jdbcTemplate.update(sql, orderItemId);
    }

    @Override
    public OrderItem getOrderItemById(Long orderItemId){
        String sql = "SELECT * FROM " + ORDER_ITEMS_TABLE_NAME + " WHERE order_item_id=?";
        return jdbcTemplate.queryForObject(sql, orderItemMapper, orderItemId);
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrderId(Long orderId) {
        String sql = "SELECT * FROM " + ORDER_ITEMS_TABLE_NAME + " WHERE order_id = ?";
        return jdbcTemplate.query(sql, orderItemMapper, orderId);
    }



}
