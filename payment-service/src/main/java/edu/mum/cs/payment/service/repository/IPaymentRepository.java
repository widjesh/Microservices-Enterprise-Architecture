package edu.mum.cs.payment.service.repository;

import edu.mum.cs.payment.service.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Payment repository.
 */
@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {
}
