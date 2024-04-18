package com.example.demo.repository;

import com.example.demo.entities.UrlClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlClass, Long> {

}
