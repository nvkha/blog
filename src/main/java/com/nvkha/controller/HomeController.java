package com.nvkha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.Thymeleaf;

@Controller
public class HomeController {
    @GetMapping({"/"})
    public String home() {
        return "index";
    }
}
