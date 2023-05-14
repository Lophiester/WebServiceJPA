package com.lophiester.webService.controllers;

import com.lophiester.webService.entities.User;
import com.lophiester.webService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> findAll() {
      return userService.findAll();
    }
}
