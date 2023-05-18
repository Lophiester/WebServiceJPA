package com.lophiester.webService.services;

import com.lophiester.webService.entities.Category;
import com.lophiester.webService.entities.dto.CategoryDTO;
import com.lophiester.webService.repositories.CategoryRepository;
import com.lophiester.webService.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = categoryRepository.findAll();
        return list.stream().map(CategoryDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.map(CategoryDTO::new).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        categoryRepository.save(category);
        return new CategoryDTO(category);
    }

    @Transactional
    public CategoryDTO update(CategoryDTO dto, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        category.setName(dto.getName());
        categoryRepository.save(category);
        return new CategoryDTO(category);
    }
    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
