package com.nvkha.controller;

import com.nvkha.model.User;
import com.nvkha.service.IUserService;
import com.nvkha.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @DeleteMapping
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId.longValue());
    }
}
