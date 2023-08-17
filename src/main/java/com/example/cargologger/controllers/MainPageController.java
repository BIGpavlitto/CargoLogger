package com.example.cargologger.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@SessionAttributes("user")
@Controller
@RequestMapping("/main")
public class MainPageController {
    List<String> initialInfo;

    public MainPageController(){
        initialInfo = new ArrayList<>();
        initialInfo.add("Cargo take from: Helsinki, to: Tallinn");
        initialInfo.add("Cargo take from: Tartu, to: Tallinn");
        initialInfo.add("Cargo take from: Tallinn, to: Tallinn");
        initialInfo.add("Cargo take from: Helsinki, to: Tallinn");
    }

    @GetMapping("/current")
    public String getMainPage(Model model) {
        model.addAttribute("initialData", initialInfo);
        return "mainPage";
    }
}
