package com.example.demo.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.demo.entities.UrlClass;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.UrlRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

  private final UrlRepository urlRepository;
  private final Logger log = LoggerFactory.getLogger(RedirectController.class);

  public RedirectController(UrlRepository urlRepository) {
    this.urlRepository = urlRepository;
  }

  @GetMapping("/{shortUrl}")
  public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl, HttpServletResponse response) {
    log.info("Searching for short URL: {}", shortUrl);
    String fullShortUrl = "http://localhost:8080/" + shortUrl;
    UrlClass url = urlRepository.findByShortUrl(fullShortUrl)
        .orElseThrow(() -> new ResourceNotFoundException("Short URL not found: " + shortUrl));
    try {
      response.sendRedirect(url.getOriginalUrl());
      log.info("Redirect attempt for short URL: {}", shortUrl);
      return ResponseEntity.status(HttpStatus.FOUND).build();
    } catch (IOException e) {
      log.error("Failed to redirect short URL: {}", shortUrl, e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

}
