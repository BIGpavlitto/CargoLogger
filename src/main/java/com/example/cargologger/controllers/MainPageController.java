package com.example.cargologger.controllers;

import com.example.cargologger.models.LoginAuthentication;
import com.example.cargologger.models.encryption.PasswordHash;
import com.example.cargologger.models.users.Driver;
import com.example.cargologger.models.users.Employer;
import com.example.cargologger.repositories.DriverRepository;
import com.example.cargologger.repositories.LoginRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/main")
public class MainPageController {

    private final DriverRepository driverRepository;
    private final LoginRepository loginRepository;
    private List<String> initialInfo;

    @Autowired
    public MainPageController(DriverRepository driverRepository, LoginRepository loginRepository){
        this.driverRepository = driverRepository;
        this.loginRepository = loginRepository;
        initialInfo = new ArrayList<>();
        initialInfo.add("27.07.23 Cargo arrival 07:12: OS-37-HP(POLAR) => Shenker, Vantaa + Espoo + Tampere +  Pori");
        initialInfo.add("28.07.23 Cargo arrival 07:30: OS-37-HP(POLAR) => Shenker, Helsinki + Porvoo + Lahti + Kuopio");
        initialInfo.add("29.07.23 Cargo arrival 07:40: OS-37-HP(POLAR) => Shenker, Helsinki + Kotka + Lappeenranta");
        initialInfo.add("30.07.23 Cargo arrival 07:50: OS-37-HP(POLAR) => Shenker, Kouvola + Mikkeli + Oulu");
    }

    @GetMapping("/current_1")
    public String getMainPageForEmployer(Model model, HttpSession session) {
        model.addAttribute("employer", session.getAttribute("employer"));
        return "mainEmployer";
    }

    @GetMapping("/current_2")
    public String getMainPageForDriver(Model model, HttpSession session) {

        model.addAttribute("initialData", initialInfo);
        model.addAttribute("driver", session.getAttribute("driver"));
        return "mainDriver";
    }
    @PostMapping("/current_2")
    public String updateDriverData(@ModelAttribute Driver driver, @ModelAttribute LoginAuthentication loginAuthentication){
        LoginAuthentication updatedLoginAuthentication;
        Driver updatedDriver;

        Optional<LoginAuthentication> wrapperEditedLoginAuth = this.loginRepository.findByUserId(driver.getId());

        if(wrapperEditedLoginAuth.isPresent()){
            updatedLoginAuthentication = wrapperEditedLoginAuth.get();
            updatedDriver = (Driver) wrapperEditedLoginAuth.get().getUser();
            updatedDriver.setName(driver.getName());
            updatedDriver.setSurname(driver.getSurname());
            updatedDriver.setEmail(driver.getEmail());
            updatedDriver.setPhoneNumber(driver.getPhoneNumber());
            updatedLoginAuthentication.setPassword(PasswordHash.hashPassword(loginAuthentication.getPassword()));
            this.driverRepository.save(updatedDriver);
            this.loginRepository.save(updatedLoginAuthentication);
        }
        return "mainDriver";
    }

    @GetMapping("personalData/{id}")
    public String getPersonalData(@PathVariable Long id, Model model){
        Optional<LoginAuthentication> wrapperForLoginDataOfDriver = loginRepository.findByUserId(id);

        if(wrapperForLoginDataOfDriver.isPresent()){
            model.addAttribute("driver", wrapperForLoginDataOfDriver.get().getUser());
            model.addAttribute("loginAuth", wrapperForLoginDataOfDriver.get());
        }
        return "personalData";
    }
}
