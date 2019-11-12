package edu.mum.cs.product.service.repository;

import edu.mum.cs.product.service.models.EProductCategory;
import edu.mum.cs.product.service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product edu.mum.cs.product.service.repository.
 */
@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find products by category list.
     *
     * @param category the category
     *
     * @return the list
     */
    List<Product> findProductsByCategory(@Param("category") EProductCategory category);

    /**
     * Find products by product number product.
     *
     * @param productNumber the product number
     *
     * @return the product
     */
    Product findProductsByProductNumber(@Param("productNumber") String productNumber);
}
