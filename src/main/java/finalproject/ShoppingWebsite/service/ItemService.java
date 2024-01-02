package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.model.Item;

import java.util.List;

public interface ItemService {
    Item getItemById(Long itemId);
    void updateItemStockQuantity(Item item, int quantity);
    Integer getStockQuantity(Long itemId);

    List<Item> getListOfAllItems();
}
