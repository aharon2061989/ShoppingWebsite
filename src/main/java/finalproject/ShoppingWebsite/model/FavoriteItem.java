package finalproject.ShoppingWebsite.model;

import finalproject.ShoppingWebsite.dto.FavoriteItemResponse;

public class FavoriteItem {
    private Long favoriteItemId;
    private Long UserId;
    private Long itemId;

    public FavoriteItem() {
    }

    public FavoriteItem(Long favoriteItemId, Long userId, Long itemId) {
        this.favoriteItemId = favoriteItemId;
        UserId = userId;
        this.itemId = itemId;
    }

    public Long getFavoriteItemId() {
        return favoriteItemId;
    }

    public void setFavoriteItemId(Long favoriteItemId) {
        this.favoriteItemId = favoriteItemId;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public FavoriteItemResponse toFavoriteItemResponse(FavoriteItem favoriteItem, Item item) {
        return new FavoriteItemResponse(
                favoriteItem.getFavoriteItemId(),
                favoriteItem.getUserId(),
                item
        );
    }
}
