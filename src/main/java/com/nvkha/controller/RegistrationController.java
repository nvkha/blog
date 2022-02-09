package com.nvkha.controller;

import com.nvkha.model.RegistrationRequest;
import com.nvkha.service.impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public String registration(@RequestBody RegistrationRequest request) {
        return registrationService.register(request.getUsername(), request.getPassword());
    }
}
