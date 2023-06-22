package com.lophiester.webService.services;

import com.lophiester.webService.entities.Product;
import com.lophiester.webService.entities.dto.ProductDTO;
import com.lophiester.webService.repositories.ProductRepository;
import com.lophiester.webService.services.exceptions.DataIntegrityException;
import com.lophiester.webService.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAll(Integer page,Integer size,String direction, String orderBy){
        PageRequest pageRequest= PageRequest.of(page,size, Sort.Direction.valueOf(direction),orderBy);
        return productRepository.findAll(pageRequest);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        Optional<Product> list = productRepository.findById(id);
        return list.orElseThrow(() -> new ObjectNotFoundException("Object not found" + Product.class.getName()));
    }


    @Transactional
    public Product save(Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

    @Transactional
    public Product update(Product oldObj) {
        Product newObj = findById(oldObj.getId());
        updateData(newObj, oldObj);
        return (newObj);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Cannot delete user with id: " + id + " because it is used in other entities");
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Object not found" + Product.class.getName() + "with id: " + id);
        }
    }


    public Product fromDTO(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        return product;
    }

    public void updateData(Product newObj, Product oldObj) {
        newObj.setName(oldObj.getName());
        newObj.setDescription(oldObj.getDescription());
        newObj.setPrice(oldObj.getPrice());
        newObj.setImgUrl(oldObj.getImgUrl());
        productRepository.save(newObj);
    }
}
