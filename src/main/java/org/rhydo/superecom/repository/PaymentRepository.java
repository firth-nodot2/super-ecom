package org.rhydo.superecom.repository;

import org.rhydo.superecom.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
