package com.lophiester.webService.services;

import com.lophiester.webService.entities.Order;
import com.lophiester.webService.repositories.OrderRepository;
import com.lophiester.webService.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException("Object not found"+Order.class.getName()+"with id:"+id));
    }
}