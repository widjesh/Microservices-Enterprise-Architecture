package edu.mum.cs.shipping.service.repository;

import edu.mum.cs.shipping.service.models.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The interface Shipping repository.
 */
@RepositoryRestResource(collectionResourceRel = "shipping", path = "shipping")
public interface IShippingRepository extends JpaRepository<Shipping, Long> {
}
