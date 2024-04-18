package com.example.demo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.demo.entities.UserClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  public static final String USER_NOT_FOUND_WITH_ID = "User not found with id: ";
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @GetMapping
  public ResponseEntity<List<UserClass>> getAllUsers() {
    log.info("Fetching all users");
    List<UserClass> users = userRepository.findAll();
    log.debug("Number of users retrieved: {}", users.size());
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserClass> getUserById(@PathVariable Long id) {
    log.info("Fetching user by id: {}", id);
    UserClass user = userRepository.findById(id)
        .orElseThrow(() -> {
          log.error("User not found with id: {}", id);
          return new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id);
        });
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserClass> createUser(@RequestBody UserClass user) {
    log.info("Creating a new user with username: {}", user.getUsername());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    UserClass createdUser = userRepository.save(user);
    log.info("User created with id: {}", createdUser.getUserId());
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserClass> updateUser(@PathVariable Long id, @RequestBody UserClass user) {
    log.info("Updating user with id: {}", id);
    UserClass updatedUser = userRepository.findById(id)
        .map(existingUser -> {
          existingUser.setUsername(user.getUsername());
          existingUser.setEmail(user.getEmail());
          existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
          log.debug("Updated user details for id: {}", id);
          return userRepository.save(existingUser);
        })
        .orElseThrow(() -> {
          log.error("Failed to find user with id: {}", id);
          return new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id);
        });
    return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    log.info("Deleting user with id: {}", id);
    userRepository.findById(id)
        .orElseThrow(() -> {
          log.error("Failed to find user to delete with id: {}", id);
          return new ResourceNotFoundException(USER_NOT_FOUND_WITH_ID + id);
        });
    userRepository.deleteById(id);
    log.info("User deleted with id: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
