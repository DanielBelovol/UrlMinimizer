package com.example.demo.services;


import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> listAll() {
        return repository.findAll();
    }
    public User add(User user) {
        return repository.save(user);
    }
    public void update(User user){
        repository.save(user);
    }
    public void deleteByID(Long id){
        repository.deleteById(id);
    }
    public User getById(Long id){
        return repository.findById(id).orElse(null);
    }
}
