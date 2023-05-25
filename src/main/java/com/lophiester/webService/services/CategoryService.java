package com.lophiester.webService.services;

import com.lophiester.webService.entities.Category;
import com.lophiester.webService.entities.dto.CategoryDTO;
import com.lophiester.webService.repositories.CategoryRepository;
import com.lophiester.webService.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found" + Category.class.getName() + "with id:" + id));
    }

    @Transactional(readOnly = true)
    public Page<Category> findPage(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return categoryRepository.findAll(pageRequest);
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
    public void deleteById(Long id) {
        findById(id);
        categoryRepository.deleteById(id);
    }

    public Category fromDTO(CategoryDTO dto) {
        return new Category(dto.getId(), dto.getName());
    }

    public void updateData(Category newObj, Category oldObj) {
        newObj.setName(oldObj.getName());
    }
}
