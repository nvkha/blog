package com.nvkha.service;

import com.nvkha.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<User> getAllUser();

    void deleteUser(Long userId);
}
