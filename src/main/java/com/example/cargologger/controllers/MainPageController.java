package com.example.cargologger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.processing.Generated;

@SessionAttributes("user")
@Controller
@RequestMapping("/main")
public class MainPageController {
    @GetMapping("/current")
    public String showMainPage() {
        return "mainPage_1";
    }
}
