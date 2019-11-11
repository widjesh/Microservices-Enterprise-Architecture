package edu.mum.cs.product.service.controller;

import edu.mum.cs.product.service.models.EProductCategory;
import edu.mum.cs.product.service.models.Product;
import edu.mum.cs.product.service.repository.IProductRepository;
import edu.mum.cs.product.service.template.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${ACCOUNT_SERVICE_IP:localhost}")
    private String accountServiceId;

    @Value("${ACCOUNT_SERVICE_PORT:3000}")
    private String accountServicePort;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") long id){
        return this.productRepository.getOne(id);
    }

    @GetMapping(value = "/category/{category}")
    public List<Product> getProduct(@PathVariable("category") EProductCategory category, @RequestHeader (name="Authorization") String token){
        Claims claims = Jwts.parser().setSigningKey("VE9QU0VDUkVU").parseClaimsJws(token.substring(7)).getBody();
        String email = claims.get("email").toString();
        String url = "http://"+ accountServiceId +":"+accountServicePort+"/find/"+email;
        User user = restTemplate.getForObject(url, User.class);
        return this.productRepository.findProductsByCategory(category);
    }

    @GetMapping
    public List<Product>  getProducts(){
        return this.productRepository.findAll();
    }

    @PostMapping
    public Product postProduct(@RequestBody Product product){
        return this.productRepository.save(product);
    }


}
