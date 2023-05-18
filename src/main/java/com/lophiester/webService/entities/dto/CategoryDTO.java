package com.lophiester.webService.entities.dto;

import com.lophiester.webService.entities.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lophiester.webService.entities.Category} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;

    public CategoryDTO(Category entity) {
        BeanUtils.copyProperties(entity, this);
    }
}