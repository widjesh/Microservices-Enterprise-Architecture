package edu.mum.cs.product.service.models;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "PRODUCT_NUMBER", nullable = false, unique = true)
    private String productNumber;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    private EProductCategory category;

}
