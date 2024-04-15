package com.example.demo.shortener;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class UrlShortener {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH_MIN = 6;
    private static final int SHORT_URL_LENGTH_MAX = 8;
    private final SecureRandom random = new SecureRandom();

    public String generateRandomString() {
        int length = random.nextInt(SHORT_URL_LENGTH_MAX - SHORT_URL_LENGTH_MIN + 1) + SHORT_URL_LENGTH_MIN;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }
}