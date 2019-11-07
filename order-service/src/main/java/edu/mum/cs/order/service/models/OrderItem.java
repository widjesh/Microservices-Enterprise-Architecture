package edu.mum.cs.order.service.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * The type Order item.
 */
@Data
@Entity
@Table(name = "ORDER_ITEMS")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ORDER_DATE")
    private LocalDate orderDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private EOrderItemStatus status;

    @Column(name = "ORDER_ID", nullable = false)
    private long orderId;

    @Column(name = "PRODUCT_ID", nullable = false)
    private long productId;

}
