package com.example.samplelogin.controller;

import com.example.samplelogin.repository.UserRepository;

import com.example.samplelogin.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Ramya Rao on 08-11-2021 18:09
 */

@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        CustomUserDetailService.display = "";
        return "login";
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {

        model.addAttribute("username", CustomUserDetailService.display);//Logged in Username
        return "index";
    }
}
