package com.nvkha.service.impl;

import com.nvkha.repository.entity.UserEntity;
import com.nvkha.repository.entity.UserRole;
import com.nvkha.service.IRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationService {
    private final UserService userService;

    @Autowired
    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String register(String username, String password) {
        return userService.signUp(new UserEntity(username, password, UserRole.USER));
    }
}
