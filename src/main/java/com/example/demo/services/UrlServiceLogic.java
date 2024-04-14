package com.example.demo.services;

import com.example.demo.entities.UrlClass;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;



@Service
public class UrlServiceLogic {
    private static final String StartOfUrl = "https://minimizer/";
    public String shortenerUrl(UrlClass originalUrl){
        String sha256 = DigestUtils.sha256Hex(originalUrl.originaUrl);

        String code = sha256.substring(0,8);

        return StartOfUrl+code;
    }
}
