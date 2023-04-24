package sen300.FinalProductService;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;

public interface ProductRepository extends MongoRepository<Product, UUID> {
    //public List<Product> findByNameOrDecription(String txt, String txt2);
    public List<Product> findByProductNameContainingIgnoreCase(String name);
}