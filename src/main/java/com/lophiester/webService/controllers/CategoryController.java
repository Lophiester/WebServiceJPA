package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.Category;
import com.lophiester.webService.entities.dto.CategoryDTO;
import com.lophiester.webService.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.net.URI.create;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Page<CategoryDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Category> categories = categoryService.findAll(page, size, orderBy, direction);
        Page<CategoryDTO> dto = categories.map(CategoryDTO::new);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(categoryService.findById(id));
    }
    @GetMapping("name/{name}")
    public ResponseEntity<Category> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(categoryService.findByName(name));
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
    public void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
