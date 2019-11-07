package edu.mum.cs.order.service.controller;

import edu.mum.cs.order.service.models.EOrderItemStatus;
import edu.mum.cs.order.service.models.Order;
import edu.mum.cs.order.service.models.OrderItem;
import edu.mum.cs.order.service.repository.IOrderItemRepository;
import edu.mum.cs.order.service.repository.IOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Order controller.
 */
@RestController
@AllArgsConstructor
public class OrderController {

    private IOrderRepository orderRepository;

    private IOrderItemRepository orderItemRepository;

    /**
     * Post order response entity.
     *
     * @param order the order
     *
     * @return the response entity
     */
    @PostMapping(value = "/order", produces = "application/hal+json")
    public ResponseEntity<Order> postOrder(@RequestBody Order order){
        Order orderObj = new Order();
        if(!order.getProductList().isEmpty()) {
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
        return ResponseEntity.ok(orderObj);

    }

}
