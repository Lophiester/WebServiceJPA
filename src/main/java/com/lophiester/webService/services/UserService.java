package com.lophiester.webService.services;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.entities.dto.UserDTO;
import com.lophiester.webService.repositories.UserRepository;
import com.lophiester.webService.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDTO::new).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }

    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Transactional
    public UserDTO update(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(user.getPhone());
        user.setPassword(userDTO.getPassword());
        userRepository.save(user);
        return new UserDTO(user);
    }
}

