package edu.mum.cs.shipping.service.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SHIPPING")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORDER_ID", nullable = false)
    private long orderId;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private long customerId;

}
