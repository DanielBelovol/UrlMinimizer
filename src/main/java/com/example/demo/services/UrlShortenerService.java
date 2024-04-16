package com.example.demo.services;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class UrlShortenerService {
    private static final String StartOfUrl = "https://minimizer/";

    public String shortenerUrl(String originalUrl) {
        String sha256 = DigestUtils.sha256Hex(originalUrl);

        String code = sha256.substring(0, 8);

        return StartOfUrl + code;
    }
}
