package com.example.User.controller;

import com.example.User.dto.LoginRequest;
import com.example.User.dto.UserRequest;
import com.example.User.dto.UserResponse;
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


    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/{userId}")
    public UserResponse getProfile(@PathVariable Long userId) {
        return userService.getProfile(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateProfile(
            @PathVariable Long userId,
            @RequestBody UserRequest request) {
        return userService.updateProfile(userId, request);
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
