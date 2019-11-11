package edu.mum.cs.payment.service.models;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Payment.
 */
@Data
@Entity
@Table(name = "PAYMENTS")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

}
