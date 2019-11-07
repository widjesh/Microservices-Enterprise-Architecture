package edu.mum.cs.order.service.repository;

import edu.mum.cs.order.service.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * The interface Order item repository.
 */
@RepositoryRestResource(collectionResourceRel = "orderItem", path = "orderItem")
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
}
