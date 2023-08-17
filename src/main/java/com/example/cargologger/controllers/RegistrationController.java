package com.example.cargologger.controllers;

import com.example.cargologger.models.users.Driver;
import com.example.cargologger.models.users.Employer;
import com.example.cargologger.models.users.User;
import com.example.cargologger.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes("user")
@Controller
public class RegistrationController {

    private UserRepository userRepository;

    @Autowired
    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registration";
    }


    @PostMapping("/registration")
    public String getUserData(@ModelAttribute User user, @RequestParam("role") String role, RedirectAttributes redirectAttributes) {
        if (role.equals("manager")) {
            Employer employer = new Employer();
            employer.setName(user.getName());
            employer.setSurname(user.getSurname());
            employer.setPhoneNumber(user.getPhoneNumber());
            employer.setEmail(user.getEmail());
            userRepository.save(employer);
        } else {
            Driver driver = new Driver();
            driver.setName(user.getName());
            driver.setSurname(user.getSurname());
            driver.setPhoneNumber(user.getPhoneNumber());
            driver.setEmail(user.getEmail());
            userRepository.save(driver);
        }
        redirectAttributes.addFlashAttribute("user", user);


        return "mainPage_1";
    }
}
