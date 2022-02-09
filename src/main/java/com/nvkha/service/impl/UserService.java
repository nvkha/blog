package com.nvkha.service.impl;

import com.nvkha.model.User;
import com.nvkha.repository.UserRepository;
import com.nvkha.repository.entity.UserEntity;
import com.nvkha.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> optional = userRepository.findByUsername(username);
        if(!optional.isPresent()) {
            throw new IllegalStateException("User not found");
        }
        return optional.get();
    }

    public String signUp(UserEntity userEntity) {
        Optional<UserEntity> optional = userRepository.findByUsername(userEntity.getUsername());
        if(optional.isPresent()) {
            throw new IllegalStateException("Username already taken");
        }
        String encode = bCryptPasswordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encode);
        userRepository.save(userEntity);
        return "ok";
    }

    @Override
    public List<User> getAllUser() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<User> users = new ArrayList<>();
        for(UserEntity userEntity : userEntities) {
            User user = new User();
            user.setId(userEntity.getId());
            user.setUsername(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setRole(userEntity.getRole());
            users.add(user);
        }
        return users;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
