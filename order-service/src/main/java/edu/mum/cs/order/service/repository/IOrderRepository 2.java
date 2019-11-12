package edu.mum.cs.order.service.repository;

import edu.mum.cs.order.service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The interface Order repository.
 */
@RepositoryRestResource(collectionResourceRel = "order", path = "order")
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
