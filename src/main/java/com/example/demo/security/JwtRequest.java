package com.example.demo.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

  private String username;
  private String password;
}
