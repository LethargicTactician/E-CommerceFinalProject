package sen300.basketservice;

//import java.time.LocalDate;
import org.springframework.data.annotation.*;
import java.io.Serializable;
import java.util.UUID;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private UUID productGuid;

	private String name;

	private double price;

	private String description;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public UUID getProductGuid() {
		return productGuid;
	}

	public void setProductGuid(UUID productGuid) {
		this.productGuid = productGuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
