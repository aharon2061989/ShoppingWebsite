package finalproject.ShoppingWebsite.repository.mapper;

import finalproject.ShoppingWebsite.model.Item;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Item(
                rs.getLong("item_id"),
                rs.getString("item_title"),
                rs.getString("photo_url"),
                rs.getDouble("item_price"),
                rs.getInt("stock_quantity")
        );
    }
}
