package com.lophiester.webService.entities.dto;

import com.lophiester.webService.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lophiester.webService.entities.Product} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private String Description;
    private Double price;
    private String imgUrl;

    public ProductDTO(Product entity) {
        BeanUtils.copyProperties(entity, this);

    }

}