package edu.mum.cs.order.service.repository;

import edu.mum.cs.order.service.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Order repository.
 */
@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
