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
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Category findById(Long id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Category save(Category obj) {
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    @Transactional
    public Category update(Category oldObj) {
        Category newObj = findById(oldObj.getId());
        updateData(newObj, oldObj);
        return categoryRepository.save(oldObj);
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Category fromDTO(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName());
    }

    public void updateData(Category newObj, Category oldObj) {
        newObj.setName(oldObj.getName());
    }
}
