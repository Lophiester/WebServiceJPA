package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.Category;
import com.lophiester.webService.entities.dto.CategoryDTO;
import com.lophiester.webService.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static java.net.URI.create;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        Category category = categoryService.fromDTO(new CategoryDTO(new Category()));
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = categoryService.fromDTO(categoryDTO);
        URI uri = create("/categories" + categoryDTO.getId());
        return ResponseEntity.created(uri).body(categoryService.save(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        Category category = categoryService.fromDTO(categoryDTO);
        category.setId(id);
        return ResponseEntity.ok().body(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
