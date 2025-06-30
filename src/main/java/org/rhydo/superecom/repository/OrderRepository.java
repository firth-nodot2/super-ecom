package org.rhydo.superecom.repository;

import org.rhydo.superecom.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
