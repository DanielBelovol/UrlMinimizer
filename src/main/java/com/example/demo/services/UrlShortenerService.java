package com.example.demo.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {

  private static final Logger log = LoggerFactory.getLogger(UrlShortenerService.class);
  private static final String StartOfUrl = "http://localhost:8080/";

  public String shortenerUrl(String originalUrl) {
    try {
      String sha256 = DigestUtils.sha256Hex(originalUrl);
      String code = sha256.substring(0, 8);
      String shortUrl = StartOfUrl + code;
      log.info("Shortened URL from {} to {}", originalUrl, shortUrl);
      return shortUrl;
    } catch (Exception e) {
      log.error("Error shortening URL: {}", originalUrl, e);
      throw new RuntimeException("Error shortening URL", e);
    }
  }
}
