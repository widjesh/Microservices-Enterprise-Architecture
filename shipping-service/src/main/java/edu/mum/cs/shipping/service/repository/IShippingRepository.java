package edu.mum.cs.shipping.service.repository;

import edu.mum.cs.shipping.service.models.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Shipping repository.
 */
@Repository
public interface IShippingRepository extends JpaRepository<Shipping, Long> {
}
