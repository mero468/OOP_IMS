public class Product{

    private String productName;
    private String expiryDate;
    private int price;
    private int quantity;

    public Product( String productName, int price, String expiryDate,int quantity) {
        this.productName = productName;
        this.expiryDate = expiryDate;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getPrice() {
        return price;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}