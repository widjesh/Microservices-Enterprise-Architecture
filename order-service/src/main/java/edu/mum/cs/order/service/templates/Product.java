package edu.mum.cs.order.service.templates;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The type Product.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private long id;

    private String productNumber;

    private String name;

    private BigDecimal price;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private EProductCategory category;

}
