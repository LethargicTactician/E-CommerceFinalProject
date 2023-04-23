package sen300.basketservice;
 
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.*;
 
//import java.time.LocalDate;
 
@RestController
@RequestMapping("/basket")
public class BasketRestController {
    @Autowired
    private RedisTemplate<String, Basket> redisTemplate;

    @PostMapping(path = "/{basketId}")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Basket addSingleBasket(@PathVariable String basketId, @RequestBody Product product)
    {
        Basket basket = null;
        if("new".equals(basketId)){
            basket =  new Basket(UUID.randomUUID().toString());
            
        } else{
            basket = redisTemplate.opsForValue().get(basketId);
        }

        basket.getProducts().add(products);

        redisTemplate.opsForValue().set(basket.get);

        return basket;

    }


    @PostMapping(path = "/addProducts/{basketId}")
    @ResponseStatus(code= HttpStatus.CREATED)
    public Basket addListOfProductsToBasket(@PathVariable String basketId, @RequestBody List<Product> products)
    {
        Basket basket = null;
        if("new".equals(basketId)){
            basket =  new Basket(UUID.randomUUID().toString());
            
        } else{
            basket = redisTemplate.opsForValue().get(basketId);
        }

        for(product product : products){
            basket.getproducts().add(product);
        }

        redisTemplate.opsForValue().set(basket.get);

        return basket;

    }

    @GetMapping(path = "/{basketId}")
    @ResponseStatus(code = HttpStatus.OK)
    public Basket getBasket(@PathVariable basketId){
        Basket basket = redisTemplate.opForValue().get(basketId);

        return basket;

    }

    @DeleteMapping(path = "/{basketId}/{productId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteproductFromBasket(@PathVariable String basketId, @PathVariable UUID productUUID){
        Basket basket = redisTemplate.opsForValue().get(basketId);
        basket.setproducts(basket.getproducts().stream().filter(b -> !b.getproductGuid().equals(productUUID)).collect(Collectors.toList()));
        redisTemplate.opsForValue().set(basketId,basket);

    }

    @DeleteMapping(path = "/{basketId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteBasket(){
        redisTemplate.delete(basketId);
    }


}