package com.example.User.controller;

import com.example.User.dto.UserRequest;
import com.example.User.entity.User;
import com.example.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }


    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
}
