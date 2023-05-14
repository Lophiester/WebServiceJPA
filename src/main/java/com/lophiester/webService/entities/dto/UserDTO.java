package com.lophiester.webService.entities.dto;

import com.lophiester.webService.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * A DTO for the {@link com.lophiester.webService.entities.User} entity
 */
@Getter
@Setter
public class UserDTO implements Serializable {
    private Long id;
    private String username;
    @Email
    private String email;
    private String phone;
    private String password;

    public UserDTO(User entity) {
        BeanUtils.copyProperties(entity, this);
    }

}