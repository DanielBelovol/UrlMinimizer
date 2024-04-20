package com.example.demo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.example.demo.entities.UrlClass;
import com.example.demo.entities.UserClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UrlShortenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
@RequiredArgsConstructor
public class UrlController {

  private static final Logger log = LoggerFactory.getLogger(UrlController.class);
  private final UrlRepository urlRepository;
  private final UrlShortenerService urlService;
  private final UserRepository userRepository;
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
  public ResponseEntity<?> createUrl(@RequestBody UrlClass urlClass, Authentication authentication) {
    if (authentication == null || !authentication.isAuthenticated()) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User must be authenticated");
    }
    UserDetails user = (UserDetails) authentication.getPrincipal();
    UserClass userEntity = userRepository.findByUsername(user.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    urlClass.setUser(userEntity);
    log.info("Creating new URL for original URL: {}", urlClass.getOriginalUrl());
    String shortUrl = urlService.shortenerUrl(urlClass.getOriginalUrl());
    urlClass.setShortUrl(shortUrl);
    UrlClass savedUrl = urlRepository.save(urlClass);
    return ResponseEntity.ok(savedUrl);
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
