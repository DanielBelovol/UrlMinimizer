package com.example.demo.controller;

import com.example.demo.entities.Url;
import com.example.demo.entities.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }


    @PostMapping("/create")
    public ModelAndView createUser(@RequestBody User user) {
        service.createUser(user);
        return findAllUrlsByUserId(user.getId());
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ModelAndView findAllUrlsByUserId(@PathVariable() Long id) {
        List<Url> urls = service.getAllUrlByUser(id);
        User user = service.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("urls", urls);
        modelAndView.addObject("user", user);
        return modelAndView;
    }


    @PostMapping("/url/delete/{id}")
    public ModelAndView deleteUrlById(@RequestParam("userId") Long userId, @PathVariable("id") Long id) {
        service.deleteById(id);
        return findAllUrlsByUserId(userId);
    }


    @PostMapping("/url/create")
    @ResponseBody
    public void createUrl(@RequestParam("theUrl") String theUrl, @RequestParam("userId") Long userId, HttpServletResponse response) throws IOException {
        service.createUrl(theUrl, userId);
        response.setStatus(200);
        response.sendRedirect("/user/" + userId);
    }


    @PostMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}

