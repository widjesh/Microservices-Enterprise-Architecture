package edu.mum.cs.product.service.models;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product.
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("id")
    private long id;

    @Column(name = "NAME", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "PRICE")
    @JsonProperty("price")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    @JsonProperty("quantity")
    private int quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "CATEGORY")
    @JsonProperty("category")
    private EProductCategory category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public EProductCategory getCategory() {
        return category;
    }

    public void setCategory(EProductCategory category) {
        this.category = category;
    }
}
