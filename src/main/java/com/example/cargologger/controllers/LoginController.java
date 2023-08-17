package com.example.cargologger.controllers;

import com.example.cargologger.models.LoginAuthentication;
import com.example.cargologger.models.encryption.PasswordHash;
import com.example.cargologger.models.users.User;
import com.example.cargologger.repositories.LoginRepository;
import com.example.cargologger.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String loginControl(Model model, @RequestParam("loginID") String loginID, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {
        Optional<LoginAuthentication> loginDataWrapper = this.loginRepository.findByLoginId(loginID);

        if (loginDataWrapper.isPresent()) {
            String hashedPassword = loginDataWrapper.get().getPassword();
            if (hashedPassword != null) {
                if (PasswordHash.checkPassword(password, hashedPassword)) {
                    User user = loginDataWrapper.get().getUser();
                    if (user != null) {
                        redirectAttributes.addFlashAttribute("user", user);
                        return "redirect:/main/current";
                    }
                } else {
                    model.addAttribute("errorMessage", "Wrong password. Try once more!");
                    model.addAttribute("login", loginID);
                    model.addAttribute("password", password);
                    return "login";
                }
            }
        } else {
            model.addAttribute("errorMessage", "User not found! Check userID!");
            model.addAttribute("login", loginID);
            model.addAttribute("password", password);
            return "login";
        }
        return null;
    }
}
