package edu.mum.cs.order.service.templates;

import lombok.Data;

import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
public class Product {

    private long id;

    private String name;

    private BigDecimal price;

    private int quantity;

    private EProductCategory category;

}
