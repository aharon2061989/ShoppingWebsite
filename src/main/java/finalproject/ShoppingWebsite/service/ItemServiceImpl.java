package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item getItemById(Long itemId) {
        Item item = itemRepository.getItemById(itemId);
        if (item != null){
            return item;
        }
        else{
            return null;
        }
    }

    @Override
    public void updateItemStockQuantity(Item item, int quantity) {
        int newStockQuantity = item.getStockQuantity() - quantity;
        item.setStockQuantity(newStockQuantity);
        itemRepository.updateItemStockQuantity(item, newStockQuantity);
    }

    @Override
    public Integer getStockQuantity(Long itemId) {
        return itemRepository.getStockQuantity(itemId);
    }

    @Override
    public List<Item> getListOfAllItems() {
        return itemRepository.getListOfAllItems();
    }

    @Override
    public List<Item> searchItemsByName(String searchTerm) {
        return itemRepository.searchItemsByName(searchTerm);
    }
}


