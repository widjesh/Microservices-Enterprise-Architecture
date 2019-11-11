package edu.mum.cs.order.service.repository;

import edu.mum.cs.order.service.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Order item repository.
 */
@Repository
public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemByOrderId(final long orderId);
}
