package com.lophiester.webService.services;

import com.lophiester.webService.entities.Product;
import com.lophiester.webService.entities.dto.ProductDTO;
import com.lophiester.webService.repositories.ProductRepository;
import com.lophiester.webService.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> list = productRepository.findById(id);
        return list.map(ProductDTO::new).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else throw new ResourceNotFoundException(id);
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        productRepository.save(product);
        return new ProductDTO(product);
    }

    @Transactional
    public ProductDTO update(ProductDTO productDTO, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImgUrl(productDTO.getImgUrl());
        productRepository.save(product);
        return new ProductDTO(product);
    }
}
