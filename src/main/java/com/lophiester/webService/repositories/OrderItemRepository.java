package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.OrderItem;
import com.lophiester.webService.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}