package edu.mum.cs.product.service.repository;

import edu.mum.cs.product.service.models.EProductCategory;
import edu.mum.cs.product.service.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * The interface Product edu.mum.cs.product.service.repository.
 */
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface IProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find products by category list.
     *
     * @param category the category
     *
     * @return the list
     */
    List<Product> findProductsByCategory(@Param("category") EProductCategory category);
}
