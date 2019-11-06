package models;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product.
 */
@Entity
@Data
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private int quantity;

    @Enumerated(EnumType.STRING)
    private EProductCategory category;

}
