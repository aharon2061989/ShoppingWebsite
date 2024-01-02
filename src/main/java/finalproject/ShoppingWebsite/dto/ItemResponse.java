package finalproject.ShoppingWebsite.dto;

public class ItemResponse {
    private Long itemId;
    private String itemTitle;
    private String photoUrl;
    private double itemPrice;

    public ItemResponse() {
    }

    public ItemResponse(Long itemId, String itemTitle, String photoUrl, double itemPrice) {
        this.itemId = itemId;
        this.itemTitle = itemTitle;
        this.photoUrl = photoUrl;
        this.itemPrice = itemPrice;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
