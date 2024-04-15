package com.example.demo.repository;

import com.example.demo.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    List<Url> findAllByUserId(Long user_id);
    Url findByShortUrl(String shortUrl);
}
