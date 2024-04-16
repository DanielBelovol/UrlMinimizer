package com.example.demo.controller;

import com.example.demo.entities.UrlClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/urls")
public class UrlController {

    private final UrlRepository urlRepository;

    @Autowired
    public UrlController(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    // Получить все URL
    @GetMapping
    public List<UrlClass> getAllUrls() {
        return urlRepository.findAll();
    }

    // Получить URL по его ID
    @GetMapping("/{id}")
    public UrlClass getUrlById(@PathVariable Long id) {
        return urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UrlClass", "id", String.valueOf(id)));
    }

    // Создать новый URL
    @PostMapping
    public UrlClass createUrl(@RequestBody UrlClass urlClass) {
        return urlRepository.save(urlClass);
    }

    // Обновить URL
    @PutMapping("/{id}")
    public UrlClass updateUrl(@PathVariable Long id, @RequestBody UrlClass urlDetails) {
        UrlClass urlClass = urlRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UrlClass", "id", String.valueOf(id)));
        urlClass.setOriginalUrl(urlDetails.getOriginalUrl());
        urlClass.setShortUrl(urlDetails.getShortUrl());
        // обновите другие поля при необходимости

        return urlRepository.save(urlClass);
    }

    // Удалить URL
    @DeleteMapping("/{id}")
    public void deleteUrl(@PathVariable Long id) {
        urlRepository.deleteById(id);
    }
}