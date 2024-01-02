package finalproject.ShoppingWebsite.repository.mapper;

import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class OrderItemMapper implements RowMapper<OrderItem>{
    @Autowired
    ItemMapper itemMapper;

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OrderItem(
                rs.getLong("order_item_id"),
                rs.getLong("order_id"),
                rs.getLong("item_id"),
                rs.getInt("quantity")
        );
    }
}
