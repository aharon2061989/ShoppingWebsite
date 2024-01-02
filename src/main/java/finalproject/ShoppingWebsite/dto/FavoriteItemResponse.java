package finalproject.ShoppingWebsite.dto;

import finalproject.ShoppingWebsite.model.Item;

public class FavoriteItemResponse {
    private Long id;
    private Long userId;
    private Item favoriteItem;

    public FavoriteItemResponse() {
    }

    public FavoriteItemResponse(Long id, Long userId, Item favoriteItem) {
        this.id = id;
        this.userId = userId;
        this.favoriteItem = favoriteItem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Item getFavoriteItem() {
        return favoriteItem;
    }

    public void setFavoriteItem(Item favoriteItem) {
        this.favoriteItem = favoriteItem;
    }
}
