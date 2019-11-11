package edu.mum.cs.order.service.models;

import edu.mum.cs.order.service.templates.Product;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * The type Order.
 */
@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ORDER_NUMBER", nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate = LocalDate.now();

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private EOrderStatus status;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private long customerId;

    @Transient
    private List<Product> productList;

    @Transient
    private List<OrderItem> orderItemList;
}
