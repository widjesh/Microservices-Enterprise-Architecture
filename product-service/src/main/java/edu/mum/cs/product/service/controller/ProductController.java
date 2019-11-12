package edu.mum.cs.product.service.controller;

import edu.mum.cs.product.service.models.EProductCategory;
import edu.mum.cs.product.service.models.Product;
import edu.mum.cs.product.service.repository.IProductRepository;
import edu.mum.cs.product.service.template.MessageTemplate;
import edu.mum.cs.product.service.template.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${ACCOUNT_SERVICE_IP:localhost}")
    private String accountServiceId;

    @Value("${ACCOUNT_SERVICE_PORT:3000}")
    private String accountServicePort;

    private IProductRepository productRepository;

    /**
     * Instantiates a new Product controller.
     *
     * @param productRepository the product repository
     */
    @Autowired
    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Gets product.
     *
     * @param id the id
     *
     * @return the product
     */
    @GetMapping(value = "/{id}")
    public Product getProduct(@PathVariable("id") long id){
        return this.productRepository.getOne(id);
    }

    /**
     * Gets product.
     *
     * @param category the category
     * @param token    the token
     *
     * @return the product
     */
    @GetMapping(value = "/category/{category}")
    public List<Product> getProduct(@PathVariable("category") EProductCategory category, @RequestHeader (name="Authorization") String token){
        return this.productRepository.findProductsByCategory(category);
    }

    /**
     * Gets product by number.
     *
     * @param number the number
     *
     * @return the product by number
     */
    @GetMapping(value = "/number/{number}", produces={"application/json;charset=UTF-8"})
    public MessageTemplate getProductByNumber(@PathVariable("number") String number){
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setMessage(this.productRepository.findProductsByProductNumber(number).getProductNumber());
        return messageTemplate;
    }

    /**
     * Get products list.
     *
     * @return the list
     */
    @GetMapping
    public List<Product>  getProducts(){
        return this.productRepository.findAll();
    }

    /**
     * Post product product.
     *
     * @param product the product
     *
     * @return the product
     */
    @PostMapping
    public Product postProduct(@RequestBody Product product){
        return this.productRepository.save(product);
    }


}
