package com.lophiester.webService.entities.dto;

import com.lophiester.webService.entities.User;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lophiester.webService.entities.User} entity
 */
@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    @Email
    private String email;


    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }

}