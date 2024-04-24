package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import com.example.demo.entities.UserClass;
import com.example.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    log.info("Спроба аутентифікації користувача: {}", usernameOrEmail);
    UserClass user = userRepository.findByUsernameOrEmail(usernameOrEmail)
        .orElseThrow(() -> {
          log.error("Користувача не знайдено: {}", usernameOrEmail);
          return new UsernameNotFoundException("Користувача не знайдено: " + usernameOrEmail);
        });

    log.info("Користувача знайдено: {}", user.getUsername());
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities(user.getIsAdmin() ? "ADMIN" : "USER")
        .accountExpired(false)
        .accountLocked(false)
        .credentialsExpired(false)
        .disabled(false)
        .build();
  }
}
