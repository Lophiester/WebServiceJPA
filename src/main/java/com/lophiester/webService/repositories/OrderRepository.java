package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}