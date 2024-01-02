package finalproject.ShoppingWebsite.repository.mapper;

import finalproject.ShoppingWebsite.model.FavoriteItem;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class FavoriteItemMapper implements RowMapper<FavoriteItem> {
    @Override
    public FavoriteItem mapRow(ResultSet rs, int i) throws SQLException {
        return new FavoriteItem(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("item_id")
        );
    }
}
