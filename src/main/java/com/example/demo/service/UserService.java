package com.example.demo.service;


import com.example.demo.entities.Url;
import com.example.demo.entities.User;
import com.example.demo.repository.UrlRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.shortener.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UrlRepository urlRepository;
    private final UrlShortener urlShortener;
    private final UserRepository userRepository;
    @Autowired
    public UserService(UrlRepository urlRepository, UrlShortener urlShortener, UserRepository userRepository) {
        this.urlRepository = urlRepository;
        this.urlShortener = urlShortener;
        this.userRepository = userRepository;
    }

    public List<Url> getAllUrls(Long id){
        return urlRepository.findAllByUserId(id);
    }

    public Url getUrlById(Long id){
        Optional<Url> url = urlRepository.findById(id);
        return url.orElse(null);
    }

    public void deleteById(Long id){
        urlRepository.deleteById(id);
    }

    public void createUrl(String theUrl, long userId){
        String shortUrl = urlShortener.generateRandomString();
        Url newUrl = new Url();
        newUrl.setUrl(theUrl);
        newUrl.setShortUrl(shortUrl);
        Optional<User> u = userRepository.findById(userId);
        if(u.isPresent()){
            User user = u.get();
            newUrl.setUser(user);
        }
        urlRepository.save(newUrl);
    }
    public String getOriginalUrl(String shortUrl){
        Url url = urlRepository.findByShortUrl(shortUrl);
        if(url != null) {
            int count = url.getCountOfCalls();
            url.setCountOfCalls(++count);
            urlRepository.save(url);
        }
        assert url != null;
        return url.getUrl();
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<Url> getAllUrlByUser(long id){
        User user = getUserById(id);
        return user.getUrlList();
    }

    public User getUserById(long id){
        User user = null;
        Optional<User> tempUser = userRepository.findById(id);
        if(tempUser.isPresent()){
            user = tempUser.get();
        }
        return user;
    }

    public void deleteUserById(long id){
        userRepository.deleteById(id);
    }

    public void createUser(User user){
        userRepository.save(user);
    }
}
