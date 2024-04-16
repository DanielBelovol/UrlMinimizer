package com.example.demo.controller;
import com.example.demo.entities.UserClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<UserClass>> getAllUsers() {
        List<UserClass> users = userRepository.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserClass> getUserById(@PathVariable Long id) {
        UserClass user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id, "id", String.valueOf(id)));
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id, "id", String.valueOf(id)));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserClass> createUser(@RequestBody UserClass user) {
        UserClass createdUser = userRepository.save(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserClass> updateUser(@PathVariable Long id, @RequestBody UserClass user) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id, "id", String.valueOf(id));
        }
        user.setUserId(id);
        UserClass updatedUser = userRepository.save(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id, "id", String.valueOf(id));
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}