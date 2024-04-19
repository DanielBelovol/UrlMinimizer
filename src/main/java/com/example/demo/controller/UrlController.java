package com.example.demo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.demo.entities.UrlClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UrlRepository;
import com.example.demo.services.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {

  private static final Logger log = LoggerFactory.getLogger(UrlController.class);
  private final UrlRepository urlRepository;
  private final UrlShortenerService urlService;

  @GetMapping
  public List<UrlClass> getAllUrls() {
    log.info("Fetching all URLs");
    return urlRepository.findAll();
  }

  @GetMapping("/{id}")
  public UrlClass getUrlById(@PathVariable Long id) {
    log.info("Fetching URL by id: {}", id);
    return urlRepository.findById(id)
        .orElseThrow(() -> {
          log.error("URL not found with id: {}", id);
          return new ResourceNotFoundException("URL not found with id: " + id);
        });
  }

  @PostMapping
  public UrlClass createUrl(@RequestBody UrlClass urlClass) {
    log.info("Creating new URL for original URL: {}", urlClass.getOriginalUrl());
    String shortUrl = urlService.shortenerUrl(urlClass.getOriginalUrl());
    urlClass.setShortUrl(shortUrl);
    UrlClass savedUrl = urlRepository.save(urlClass);
    log.info("Created URL with short version: {}", savedUrl.getShortUrl());
    return savedUrl;
  }

  @PutMapping("/{id}")
  public UrlClass updateUrl(@PathVariable Long id, @RequestBody UrlClass urlDetails) {
    log.info("Updating URL with id: {}", id);
    return urlRepository.findById(id)
        .map(existingUrl -> {
          existingUrl.setOriginalUrl(urlDetails.getOriginalUrl());
          existingUrl.setShortUrl(urlService.shortenerUrl(urlDetails.getOriginalUrl()));
          log.info("Updated URL with id: {}", id);
          return urlRepository.save(existingUrl);
        })
        .orElseThrow(() -> {
          log.error("URL not found with id: {}", id);
          return new ResourceNotFoundException("URL not found with id: " + id);
        });
  }

  @DeleteMapping("/{id}")
  public void deleteUrl(@PathVariable Long id) {
    log.info("Deleting URL with id: {}", id);
    urlRepository.findById(id)
        .orElseThrow(() -> {
          log.error("URL not found with id: {}", id);
          return new ResourceNotFoundException("URL not found with id: " + id);
        });
    urlRepository.deleteById(id);
    log.info("Deleted URL with id: {}", id);
  }
}
