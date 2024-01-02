package finalproject.ShoppingWebsite.service;

import finalproject.ShoppingWebsite.dto.FavoriteItemResponse;
import finalproject.ShoppingWebsite.model.FavoriteItem;

import java.util.List;

public interface FavoriteItemService {
    void createFavoriteItem(FavoriteItem favoriteItem);
    void deleteFavoriteItem(Long favoriteItemId);
    List<FavoriteItemResponse> getAllFavoriteItemsByUserId(Long userId);}
