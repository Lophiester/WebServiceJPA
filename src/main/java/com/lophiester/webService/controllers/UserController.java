package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.entities.dto.UserDTO;
import com.lophiester.webService.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static java.net.URI.create;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /*@GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = users.stream().map(UserDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(usersDTO);
    }*/

    @GetMapping()
    public ResponseEntity<User> findById(@RequestParam(value = "id",defaultValue = "") @PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<UserDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "username") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<User> users = userService.findAll(page, linesPerPage, orderBy, direction);
        Page<UserDTO> usersDTO = users.map(UserDTO::new);
        return ResponseEntity.ok().body(usersDTO);
    }

    @PostMapping
    public ResponseEntity<User> save(@Valid @RequestBody UserDTO dto) {
        User user = userService.fromDTO(dto);
        URI uri = create("/users" + dto.getId());
        return ResponseEntity.created(uri).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        User user = userService.fromDTO(dto);
        user.setId(id);
        return ResponseEntity.ok().body(userService.update(user));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteById(@RequestParam(value = "id",defaultValue = "")@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}



