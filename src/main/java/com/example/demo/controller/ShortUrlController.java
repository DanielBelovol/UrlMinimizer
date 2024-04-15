package com.example.demo.controller;

import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class ShortUrlController {
    private final UserService service;

    @Autowired
    public ShortUrlController(UserService service){
        this.service = service;
    }


    @GetMapping("/{shortUrl:[a-zA-Z0-9]{6,8}}")
    public void redirectToOriginalUrl(@PathVariable("shortUrl") String s, HttpServletResponse response) throws IOException {
        String url = service.getOriginalUrl(s);
        response.sendRedirect(url);
    }
}
