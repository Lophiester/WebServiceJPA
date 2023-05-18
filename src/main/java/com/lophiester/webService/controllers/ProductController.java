package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.dto.ProductDTO;
import com.lophiester.webService.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.net.URI.create;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = productService.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO productDTO) {
        URI uri = create("/products" + productDTO.getId());
        return ResponseEntity.created(uri).body(productService.save(productDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        return ResponseEntity.ok().body(productService.update(productDTO, id));

    }
}
