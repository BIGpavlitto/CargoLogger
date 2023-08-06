package com.example.cargologger.controllers;

import com.example.cargologger.models.LoginAuthentication;
import com.example.cargologger.models.encryption.PasswordHash;
import com.example.cargologger.models.users.User;
import com.example.cargologger.repositories.LoginRepository;
import com.example.cargologger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SessionAttributes("user")
@Controller
public class LoginController {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public LoginController(UserRepository userRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
    }

    @ModelAttribute(name = "user")
    public User user() {
        return new User();
    }

    @ModelAttribute
    public void addPreloadedHashedPasswords() {
//        String pass_1 = PasswordHash.hashPassword("QntuyT");
//        String pass_2 = PasswordHash.hashPassword("UbsfsfT");
//        System.out.println(pass_1);
//        System.out.println(pass_2);
    }

    @GetMapping("/login")
    public String showRegistration() {
        return "login";
    }

    @PostMapping("/login")
    public String getUserData(@RequestParam("loginID") String loginID, @RequestParam("password") String password) {
        Optional<LoginAuthentication> loginData = this.loginRepository.findByLoginId(loginID);
        if (loginData.isPresent()) {
            String hashedPassword = loginData.get().getPassword();
            if (hashedPassword != null) {
                if (PasswordHash.checkPassword(password, hashedPassword)) {
                    User user = loginData.get().getUser();
                    if (user != null) {
                        return "redirect:/main/current";
                    }
                }
            }
        }
        return "redirect:/registration";
    }
}
