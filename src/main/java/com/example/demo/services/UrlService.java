package com.example.demo.services;


import com.example.demo.entities.UrlClass;
import com.example.demo.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final UrlRepository repository;

    public List<UrlClass> listAll() {
        return repository.findAll();
    }

    public UrlClass add(UrlClass url) {
        return repository.save(url);
    }

    public void update(UrlClass url) {
        repository.save(url);
    }

    public void deleteByID(Long id) {
        repository.deleteById(id);
    }

    public UrlClass getById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
