package com.nvkha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @GetMapping
    public String signup(@RequestParam Optional<Boolean> isSuccess,
                         Model model) {
        if(isSuccess.isPresent()) {
            if(isSuccess.get()) {
                model.addAttribute("message", "Signup success");
                model.addAttribute("type", "success");
            }
            else {
                model.addAttribute("message", "Username has taken");
                model.addAttribute("type", "danger");
            }
        }
        return "signup";
    }
}
