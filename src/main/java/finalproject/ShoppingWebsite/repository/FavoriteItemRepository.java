package finalproject.ShoppingWebsite.repository;

import finalproject.ShoppingWebsite.model.FavoriteItem;

import java.util.List;

public interface FavoriteItemRepository {
    void createFavoriteItem(FavoriteItem favoriteItem);
    void deleteFavoriteItem(Long favoriteItemId);
    List<FavoriteItem> getAllFavoriteItemsByUserId(Long userId);
}
