package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.repository.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository{
    private static final String ITEM_DETAILS_TABLE_NAME = "item_details";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ItemMapper itemMapper;

    @Override
    public Item getItemById(Long itemId) {
        String sql = "SELECT * FROM " + ITEM_DETAILS_TABLE_NAME + " WHERE item_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, itemMapper,itemId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    @Override
    public void updateItemStockQuantity(Item item, int quantity) {
        String sql = "UPDATE " + ITEM_DETAILS_TABLE_NAME + " SET stock_quantity = ? WHERE item_id = ?";
        jdbcTemplate.update(sql, quantity, item.getItemId());
    }

    @Override
    public Integer getStockQuantity(Long itemId){
        String sql = "SELECT stock_quantity FROM " + ITEM_DETAILS_TABLE_NAME + " WHERE item_id=?";
        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, itemId);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Item> getListOfAllItems() {
        String sql = "SELECT * FROM " + ITEM_DETAILS_TABLE_NAME;
        try {
            return jdbcTemplate.query(sql, itemMapper);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
