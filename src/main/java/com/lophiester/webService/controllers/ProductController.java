package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.Product;
import com.lophiester.webService.entities.dto.ProductDTO;
import com.lophiester.webService.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static java.net.URI.create;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<Product> list = productService.findAll();
        List<ProductDTO> listDTO = list.stream().map(ProductDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.fromDTO(productDTO);
        URI uri = create("/products" + productDTO.getId());
        productService.save(product);
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        Product product = productService.fromDTO(productDTO);
        product.setId(id);
        return ResponseEntity.ok().body(productService.update(product));

    }
}
