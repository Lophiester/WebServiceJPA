package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.entities.dto.UserDTO;
import com.lophiester.webService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Iterable<UserDTO>> findAll() {
        Iterable<UserDTO> list = userService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
        userService.save(userDTO);
        URI uri = URI.create("/users/" + userDTO.getId());
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable Long id) {
        userService.update(userDTO, id);
        return ResponseEntity.ok().body(userDTO);
    }


}

