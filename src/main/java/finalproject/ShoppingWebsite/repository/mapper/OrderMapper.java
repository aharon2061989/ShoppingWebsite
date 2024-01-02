package finalproject.ShoppingWebsite.repository.mapper;

import finalproject.ShoppingWebsite.model.Order;
import finalproject.ShoppingWebsite.model.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class OrderMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        String orderStatusString = rs.getString("order_status");
        OrderStatus orderStatus = OrderStatus.valueOf(orderStatusString);
        java.util.Date orderDate = new java.util.Date(rs.getDate("order_date").getTime());
       return new Order(
               rs.getLong("order_id"),
                rs.getLong("order_user_id"),
                orderDate,
                rs.getString("shipping_city"),
                rs.getString("shipping_country"),
                rs.getString("shipping_address"),
                rs.getDouble("total_price"),
                orderStatus
        );
    }
}
