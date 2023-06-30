package com.lophiester.webService.repositories;

import com.lophiester.webService.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByNameIgnoreCase(String name);
}