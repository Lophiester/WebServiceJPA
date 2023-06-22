package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.Order;
import com.lophiester.webService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/page")
    public ResponseEntity<Page<Order>> findPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "orderBy", defaultValue = "id") String sort,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Order> orders = orderService.findAll(page, size, sort, direction);
        return ResponseEntity.ok().body(orders);
    }
    @GetMapping()
    public ResponseEntity<Order> findById(@RequestParam(value = "id",defaultValue = "")@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }
}
