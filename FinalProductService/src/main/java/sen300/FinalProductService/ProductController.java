package sen300.FinalProductService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //Create a product(s)
        //single product
    @PostMapping(path="")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createProduct(@RequestBody Product product){
        productRepository.save(product);
    }

        //multiple products
    @PostMapping(path = "/addMiltipleProducts")
    @ResponseStatus(code = HttpStatus.OK)
    public void createMultiplePeoducts(@RequestBody List<Product> products){
        for(Product product : products){
            productRepository.save(product);
        }
    }

    //get product(s)
    // @GetMapping(path = "/search/{searchText}")
    // @ResponseStatus(code = HttpStatus.OK)
    // public List<Product> searchProducts(@PathVariable(required = true) String searchText){
    //     return productRepository.findByNameOrDecription(searchText, searchText);
    // }

    // Get a single product by UUID -> doesnt work
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Product> product = productRepository.findById(uuid);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //get by name since I gave up on uuid
    @GetMapping("/name/{productName}")
    public List<Product> getProductByName(@PathVariable String productName) {
        return productRepository.findByProductNameContainingIgnoreCase(productName);
    }   
    
    

    // Get all products
    @GetMapping(path = "")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //update product(s)
    @PutMapping(path = "/update/{productUUID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable(required = true) UUID productUUID, @RequestBody Product product){
        if (!product.getProductUUID().equals(productUUID)) {
            throw new RuntimeException("L, this didnt work");
        }

        productRepository.save(product);
    
    }

    
    // @PutMapping(path = "/{productUUID}")
	// @ResponseStatus(HttpStatus.OK)
	// public void updateBook(@PathVariable(required = true) UUID productUUID, @RequestBody Product product) {
        

	// 	if (!product.getProductUUID().equals(productUUID)) {
	// 		throw new RuntimeException("the two values did not match");
	// 	}

	// 	productRepository.save(product);
	// }
    

    // @PutMapping(path = "/{productUUID}")
	// @ResponseStatus(HttpStatus.OK)
	// public Product updateBook(@RequestBody Product product) {
    //     return product;    
	// }

    // @PutMapping(path ="/{productUUID}")
    // @ResponseStatus(code = HttpStatus.OK)
    // public void updateProduct(@PathVariable(required = true) UUID productUUID, @RequestBody Product product){
           
    //     if(product.getProductUuid() == null || !product.getProductUuid().toString().equals(productUUID.toString())){
    //         throw new RuntimeException("Product UUID does not match.");
    //     }  
    //     productRepository.save(product);
    //     //System.out.println("HI");
    // } 
    
    // delete product(s)

    @DeleteMapping(path ="/{id}")
    public void deleteProduct(@PathVariable UUID id) {
        productRepository.deleteById(id);
    }
    
    

}
