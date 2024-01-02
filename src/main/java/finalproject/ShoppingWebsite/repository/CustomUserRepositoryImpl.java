package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.CustomUser;
import finalproject.ShoppingWebsite.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {

    private static final String USER_DETAILS_TABLE_NAME = "user_details";

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserMapper userMapper;

    @Override
    public void createUser(CustomUser customUser) {
        String sql = "INSERT INTO " + USER_DETAILS_TABLE_NAME + " (first_name, last_name, email, phone_number, city, country, address, username, password, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(),
                customUser.getPhoneNumber(), customUser.getCity(), customUser.getCountry(), customUser.getAddress(), customUser.getUsername(),
                customUser.getPassword(), customUser.getActive()
        );
    }

    @Override
    public void updateUser(CustomUser customUser) {
        String sql = "UPDATE " + USER_DETAILS_TABLE_NAME + " SET first_name=?, last_name=?, email=?, phone_number=?, city=?, country=?, address=?, username=?, password=?, active=? WHERE user_id=?";
        jdbcTemplate.update(
                sql,
                customUser.getFirstName(), customUser.getLastName(), customUser.getEmail(),
                customUser.getPhoneNumber(), customUser.getCity(), customUser.getCountry(), customUser.getAddress(), customUser.getUsername(),
                customUser.getPassword(), customUser.getActive()
        );
    }

    @Override
    public void deleteUserById(Long id) {
        String sql = "DELETE FROM " + USER_DETAILS_TABLE_NAME + " WHERE user_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public CustomUser findUserByUsername(String username) {
        String sql = "SELECT * FROM " + USER_DETAILS_TABLE_NAME + " WHERE username=?";
        try {
            return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
        } catch (EmptyResultDataAccessException error) {
            return null;
        }
    }

    @Override
    public CustomUser getUserById(Long userId) {
        String sql = "SELECT * FROM " + USER_DETAILS_TABLE_NAME + " WHERE user_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, userMapper, userId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
