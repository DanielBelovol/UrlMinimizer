package com.example.demo.services;


import com.example.demo.entities.UserClass;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<UserClass> listAll() {
        return repository.findAll();
    }

    public UserClass add(UserClass user) {
        return repository.save(user);
    }

    public void update(UserClass user) {
        repository.save(user);
    }

    public void deleteByID(Long id) {
        repository.deleteById(id);
    }

    public UserClass getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
