package edu.mum.cs.order.service.controller;

import edu.mum.cs.order.service.models.EOrderItemStatus;
import edu.mum.cs.order.service.models.Order;
import edu.mum.cs.order.service.models.OrderItem;
import edu.mum.cs.order.service.repository.IOrderItemRepository;
import edu.mum.cs.order.service.repository.IOrderRepository;
import edu.mum.cs.order.service.template.User;
import edu.mum.cs.order.service.templates.Product;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Order controller.
 */
@RestController
public class OrderController {

    @Value("${ACCOUNT_SERVICE_IP:localhost}")
    private String accountServiceId;

    @Value("${ACCOUNT_SERVICE_PORT:3000}")
    private String accountServicePort;

    private IOrderRepository orderRepository;

    private IOrderItemRepository orderItemRepository;

    private RestTemplate restTemplate;

    /**
     * Instantiates a new Order controller.
     *
     * @param orderRepository     the order repository
     * @param orderItemRepository the order item repository
     * @param restTemplate        the rest template
     */
    @Autowired
    public OrderController(IOrderRepository orderRepository,IOrderItemRepository orderItemRepository,RestTemplate restTemplate){
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.restTemplate = restTemplate;
    }


    /**
     * Post order response entity.
     *
     * @param order the order
     * @param token the token
     *
     * @return the response entity
     */
    @PostMapping(value = "/order")
    public Order postOrder(@RequestBody Order order, @RequestHeader (name="Authorization") String token){
        Claims claims = Jwts.parser().setSigningKey("VE9QU0VDUkVU").parseClaimsJws(token.substring(7)).getBody();
        String email = claims.get("email").toString();
        String url = "http://"+ accountServiceId +":"+accountServicePort+"/find/"+email;
        User user = restTemplate.getForObject(url, User.class);
        Order orderObj = new Order();
        if(!order.getProductList().isEmpty()) {
            orderObj.setCustomerId(user.getId());
            orderObj = this.orderRepository.save(order);
            order.getProductList().forEach(
                    product -> {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setStatus(EOrderItemStatus.ORDERED);
                        orderItem.setOrderId(order.getId());
                        orderItem.setProductId(product.getId());
                        this.orderItemRepository.save(orderItem);
                    });
        }
        return orderObj;
    }

    /**
     * Get orders list.
     *
     * @return the list
     */
    @GetMapping(value = "/order")
    public List<Order> getOrders(){
        return  this.orderRepository.findAll();
    }


    /**
     * Gets order.
     *
     * @param id the id
     *
     * @return the order
     */
    @GetMapping(value = "/order/{id}")
    public Order getOrder(@PathVariable("id") long id){
        Order order = this.orderRepository.getOne(id);
        order.setOrderItemList(this.orderItemRepository.findOrderItemByOrderId(order.getId()));
        return order;
    }

}
