package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.FavoriteItem;
import finalproject.ShoppingWebsite.repository.mapper.FavoriteItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class FavoriteItemRepositoryImpl implements FavoriteItemRepository{

    private static final String FAVORITE_ITEM_TABLE_NAME = "favorite_item";
    @Autowired
    FavoriteItemMapper favoriteItemMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void createFavoriteItem(FavoriteItem favoriteItem) {
        if (!isFavoriteItemExists(favoriteItem.getUserId(), favoriteItem.getItemId())) {
            String sql = "INSERT INTO " + FAVORITE_ITEM_TABLE_NAME + "(user_id, item_id) values (?, ?)";
            jdbcTemplate.update(
                    sql,
                    favoriteItem.getUserId(),
                    favoriteItem.getItemId()
            );
            Long favoriteItemId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID();", Long.class);
            favoriteItem.setFavoriteItemId(favoriteItemId);
        }
    }
    private boolean isFavoriteItemExists(Long userId, Long itemId) {
        String sql = "SELECT COUNT(*) FROM " + FAVORITE_ITEM_TABLE_NAME + " WHERE user_id=? AND item_id=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userId, itemId);
        return count > 0;
    }


    @Override
    public void deleteFavoriteItem(Long favoriteItemId) {
        String sql = "DELETE FROM " + FAVORITE_ITEM_TABLE_NAME + " WHERE item_id=?";
        jdbcTemplate.update(sql, favoriteItemId);
    }

    @Override
    public List<FavoriteItem> getAllFavoriteItemsByUserId(Long userId) {
        String sql = "SELECT * FROM " + FAVORITE_ITEM_TABLE_NAME + " WHERE user_id=?";
        try {
            return jdbcTemplate.query(sql, favoriteItemMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
