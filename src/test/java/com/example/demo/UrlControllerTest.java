package com.example.demo;

import static org.mockito.Mockito.*;

import java.util.Optional;

import com.example.demo.controller.UrlController;
import com.example.demo.entities.UrlClass;
import com.example.demo.entities.UserClass;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UrlShortenerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
public class UrlControllerTest {

  @Mock
  private UrlRepository urlRepository;

  @Mock
  private UrlShortenerService urlShortenerService;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UrlController urlController;

  @Test
  public void testCreateUrl() {
    // Mock Authentication
    UserDetails userDetails = User.withUsername("test@example.com").password("password").roles("USER").build();
    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());

    // Mock URL and User
    UrlClass url = new UrlClass();
    url.setOriginalUrl("https://www.example.com");
    url.setShortUrl("https://short.ly/abc123");
    UserClass user = new UserClass();
    user.setEmail("test@example.com");

    when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
    when(urlShortenerService.shortenerUrl("https://www.example.com")).thenReturn("https://short.ly/abc123");
    when(urlRepository.save(any(UrlClass.class))).thenReturn(url);

    // Test
    ResponseEntity<?> response = urlController.createUrl(url, authentication);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(url, response.getBody());
  }

  @Test
  public void testUpdateUrl() {
    Long urlId = 1L;
    UrlClass existingUrl = new UrlClass();
    existingUrl.setLinkId(urlId);
    existingUrl.setOriginalUrl("https://www.example.com");
    existingUrl.setShortUrl("https://short.ly/abc123");

    UrlClass updatedUrl = new UrlClass();
    updatedUrl.setOriginalUrl("https://www.updated-example.com");
    updatedUrl.setShortUrl("https://short.ly/def456");

    when(urlRepository.findById(urlId)).thenReturn(Optional.of(existingUrl));
    when(urlShortenerService.shortenerUrl("https://www.updated-example.com")).thenReturn("https://short.ly/def456");
    when(urlRepository.save(existingUrl)).thenReturn(updatedUrl);

    UrlClass result = urlController.updateUrl(urlId, updatedUrl);

    Assertions.assertEquals(updatedUrl, result);
  }

  @Test
  public void testDeleteUrl() {
    Long urlId = 1L;
    UrlClass existingUrl = new UrlClass();
    existingUrl.setLinkId(urlId);
    existingUrl.setOriginalUrl("https://www.example.com");
    existingUrl.setShortUrl("https://short.ly/abc123");

    when(urlRepository.findById(urlId)).thenReturn(Optional.of(existingUrl));

    urlController.deleteUrl(urlId);

    verify(urlRepository, times(1)).deleteById(urlId);
  }
}
