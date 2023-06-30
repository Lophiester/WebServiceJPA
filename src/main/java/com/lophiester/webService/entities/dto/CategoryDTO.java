package com.lophiester.webService.entities.dto;

import com.lophiester.webService.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
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
    @NotEmpty(message = "Name cannot be empty")
    @Length(min=5, max=80, message = "Name must be between 5 and 80 characters long")
    private String name;

    public CategoryDTO(Category entity) {
        BeanUtils.copyProperties(entity, this);
    }
}