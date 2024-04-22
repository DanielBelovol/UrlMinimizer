package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String login(@RequestParam(value = "error", required = false) String error,
                      @RequestParam(value = "logout", required = false) String logout,
                      Model model) {
    if (error != null) {
      model.addAttribute("message", "Your username and password are invalid.");
    }
    if (logout != null) {
      model.addAttribute("message", "You have been logged out successfully.");
    }
    return "login";
  }

  @GetMapping("/logout-success")
  public String logout(Model model) {
    model.addAttribute("message", "You have been logged out successfully.");
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
