package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.Item;

import java.util.List;

public interface ItemRepository {
    Item getItemById(Long itemId);

    void updateItemStockQuantity(Item item, int quantity);

    Integer getStockQuantity(Long itemId);
    List<Item> getListOfAllItems();

    List<Item> searchItemsByName(String searchTerm);
}
