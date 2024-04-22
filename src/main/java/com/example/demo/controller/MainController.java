package com.example.demo.controller;

import com.example.demo.entities.UserClass;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository usersRepository;


    @GetMapping
    public String index(Model model) {
        Iterable<UserClass> users = usersRepository.findAll();
        model.addAttribute("users", users);
        return "index";
    }
    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }
    @GetMapping("/reg")
    public String reg(Model model) {
        return "reg";
    }
    @GetMapping("/acount")
    public String acount(Model model) {
        return "acount";
    }

}
