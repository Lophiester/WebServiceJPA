package com.lophiester.webService.services;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.entities.dto.UserDTO;
import com.lophiester.webService.repositories.UserRepository;
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
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<User> findAll(Integer page, Integer size, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy);
        return userRepository.findAll(pageRequest);

    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Object not found " + User.class.getName() + " with id: " + id));
    }


    @Transactional
    public User save(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    @Transactional

    public User update(User oldObj) {
        User newObj = findById(oldObj.getId());
        updateData(newObj, oldObj);
        return newObj;

    }

    @Transactional

    public void deleteById(Long id) {
        findById(id);
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityException e) {
            throw new DataIntegrityException("Cannot delete user with id: " + id + " because it is used in other entities");
        }
    }

    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public void updateData(User newObj, User oldObj) {
        newObj.setUsername(oldObj.getUsername());
        newObj.setEmail(oldObj.getEmail());
        userRepository.save(newObj);
    }
}

