package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.FavoriteItemResponse;
import finalproject.ShoppingWebsite.model.FavoriteItem;
import finalproject.ShoppingWebsite.model.Item;
import finalproject.ShoppingWebsite.repository.FavoriteItemRepository;
import finalproject.ShoppingWebsite.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FavoriteItemServiceImpl implements FavoriteItemService{

    @Autowired
    FavoriteItemRepository favoriteItemRepository;
    @Autowired
    ItemRepository itemRepository;

    @Override
    public void createFavoriteItem(FavoriteItem favoriteItem) {
        favoriteItemRepository.createFavoriteItem(favoriteItem);
    }

    @Override
    public void deleteFavoriteItem(Long favoriteItemId) {
        favoriteItemRepository.deleteFavoriteItem(favoriteItemId);
    }

    @Override
    public List<FavoriteItemResponse> getAllFavoriteItemsByUserId(Long userId) {
        List<FavoriteItem> favoriteItems = favoriteItemRepository.getAllFavoriteItemsByUserId(userId);
        List<FavoriteItemResponse> favoriteItemResponses = new ArrayList<>();
        for (FavoriteItem favoriteItem : favoriteItems) {
            Item item = itemRepository.getItemById(favoriteItem.getItemId());
            FavoriteItemResponse favoriteItemResponse = favoriteItem.toFavoriteItemResponse(favoriteItem, item);
            favoriteItemResponses.add(favoriteItemResponse);
        }
        return favoriteItemResponses;
    }
}
