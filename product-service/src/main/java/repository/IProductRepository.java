package repository;

import models.EProductCategory;
import models.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface IProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findProductsByCategory(@Param("category") EProductCategory category);
}
