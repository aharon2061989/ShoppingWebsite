package finalproject.ShoppingWebsite.model;

import finalproject.ShoppingWebsite.dto.ItemResponse;

import java.math.BigDecimal;

public class Item {
    private Long ItemId;
    private String itemTitle;
    private String photoUrl;
    private Double itemPrice;
    private int stockQuantity;

    public Item() {
    }

    public Item(Long itemId, String itemTitle, String photoUrl, Double itemPrice, int stockQuantity) {
        ItemId = itemId;
        this.itemTitle = itemTitle;
        this.photoUrl = photoUrl;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
    }

    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    public ItemResponse toItemResponse(){
        return new ItemResponse(
                this.getItemId(),
                this.getItemTitle(),
                this.getPhotoUrl(),
                this.getItemPrice()
        );
    }
}
