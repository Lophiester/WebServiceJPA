package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.Product;
import com.lophiester.webService.entities.dto.ProductDTO;
import com.lophiester.webService.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Product>> findAll() {
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "username") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Product> list = productService.findPage(page, size, direction, orderBy);
        Page<ProductDTO> listDTO = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDTO);
    }


    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody ProductDTO productDTO) {
        Product product = productService.fromDTO(productDTO);
        URI uri = create("/products" + productDTO.getId());
        return ResponseEntity.created(uri).body(productService.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @RequestBody ProductDTO productDTO, @PathVariable Long id) {
        Product product = productService.fromDTO(productDTO);
        product.setId(id);
        return ResponseEntity.ok().body(productService.update(product));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
