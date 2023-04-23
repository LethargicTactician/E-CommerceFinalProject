package sen300.FinalProductService;
import org.springframework.data.annotation.Id;

public class Product {
    //@Id
    private String productUUID;
    private String productName;
    private double price;
    private String description;


    //getters and setters
    public String getProductUuid() {
        return productUUID;
    }
    public void setProductUuid(String productUUID) {
        this.productUUID = productUUID;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    


}
