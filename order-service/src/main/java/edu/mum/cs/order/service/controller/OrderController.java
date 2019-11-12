package edu.mum.cs.order.service.controller;

import edu.mum.cs.order.service.models.EOrderItemStatus;
import edu.mum.cs.order.service.models.Order;
import edu.mum.cs.order.service.models.OrderItem;
import edu.mum.cs.order.service.repository.IOrderItemRepository;
import edu.mum.cs.order.service.repository.IOrderRepository;
import edu.mum.cs.order.service.templates.MessageTemplate;
import edu.mum.cs.order.service.templates.Product;
import edu.mum.cs.order.service.templates.Shipping;
import edu.mum.cs.order.service.templates.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * The type Order controller.
 */
@Slf4j
@RestController
public class OrderController {

    private static final String PROTOCOL = "http://";

    @Value("${ACCOUNT_SERVICE_IP:localhost}")
    private String accountServiceIp;

    @Value("${ACCOUNT_SERVICE_PORT:3000}")
    private String accountServicePort;

    @Value("${PAYMENT_SERVICE_IP:localhost}")
    private String paymentServiceIp;

    @Value("${PAYMENT_SERVICE_PORT:3007}")
    private String paymentServicePort;

    @Value("${PRODUCT_SERVICE_IP:localhost}")
    private String productServiceIp;

    @Value("${PRODUCT_SERVICE_PORT:3001}")
    private String productServicePort;

    @Value("${SHIPPING_SERVICE_IP:localhost}")
    private String shippingServiceIp;

    @Value("${SHIPPING_SERVICE_PORT:3006}")
    private String shippingServicePort;

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
    public OrderController(IOrderRepository orderRepository, IOrderItemRepository orderItemRepository, RestTemplate restTemplate) {
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
    public Order postOrder(@RequestBody Order order, @RequestHeader(name = "Authorization") String token) {
        Claims claims = Jwts.parser().setSigningKey("VE9QU0VDUkVU").parseClaimsJws(token.substring(7)).getBody();
        String email = claims.get("email").toString();
        String accountAddress = PROTOCOL + accountServiceIp + ":" + accountServicePort + "/find/" + email;
        String paymentAddress = PROTOCOL + paymentServiceIp + ":" + paymentServicePort + "/payment";
        String shippingAddress = PROTOCOL + shippingServiceIp + ":" + shippingServicePort + "/shipping/save";

        User user = restTemplate.getForObject(accountAddress, User.class);
        order.getPayment().setOrderNumber(order.getOrderNumber());
        MessageTemplate messageTemplate = restTemplate.postForObject(paymentAddress, order.getPayment(), MessageTemplate.class);
        MessageTemplate messageTemplateObj = null;
        Order orderObj = new Order();
        if (messageTemplate != null && messageTemplate.getMessage().toLowerCase().contains("success")
                && user != null && !order.getProductList().isEmpty()) {
            orderObj.setCustomerId(user.getId());
            orderObj = this.orderRepository.save(order);
            for (Product product : order.getProductList()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setStatus(EOrderItemStatus.ORDERED);
                orderItem.setOrderId(orderObj.getId());
                orderItem.setProductNumber(product.getProductNumber());
                this.orderItemRepository.save(orderItem);
            }
            orderObj.setOrderItemList(this.orderItemRepository.findOrderItemByOrderId(orderObj.getId()));
            Shipping shipping = new Shipping();
            shipping.setOrderId(orderObj.getId());
            shipping.setCustomerId(user.getId());
            try {
                restTemplate.postForObject(shippingAddress, shipping, MessageTemplate.class);
            }catch (Exception ex){
                log.error(ex.getMessage());
            }

        }
        orderObj.setMessage("Shipment has been placed...");
        return orderObj;
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    @GetMapping(value = "/order")
    public List<Order> getOrders() {
        return this.orderRepository.findAll();
    }


    /**
     * Gets order.
     *
     * @param id the id
     *
     * @return the order
     */
    @GetMapping(value = "/order/{id}")
    public Order getOrder(@PathVariable("id") long id) {
        Order order = this.orderRepository.getOne(id);
        order.setOrderItemList(this.orderItemRepository.findOrderItemByOrderId(order.getId()));
        return order;
    }

}
