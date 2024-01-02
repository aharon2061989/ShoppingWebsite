package finalproject.ShoppingWebsite.repository.mapper;

import finalproject.ShoppingWebsite.model.CustomUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class UserMapper implements RowMapper<CustomUser> {
    @Override
    public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CustomUser(
                rs.getLong("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getString("city"),
                rs.getString("country"),
                rs.getString("address"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getInt("active")
        );
    }
}
