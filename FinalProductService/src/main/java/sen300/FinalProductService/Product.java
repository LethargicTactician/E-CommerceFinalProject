package sen300.FinalProductService;
import java.util.*;

import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    
    @Id
    private UUID productUUID;
    private String productName;
    private double price;
    private String description;

    public Product() {
        this.productUUID = UUID.randomUUID();
        this.productName = null;
        this.price = 0;
        this.description = null;
    }

    //getters and setters
    public UUID getProductUUID() {
        return productUUID;
    }
    public void setProductUUID(UUID productUUID) {
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
