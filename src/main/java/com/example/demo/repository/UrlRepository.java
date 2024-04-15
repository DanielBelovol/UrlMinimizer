package com.example.demo.repository;

import com.example.demo.entities.UrlClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Long, UrlClass> {
}
