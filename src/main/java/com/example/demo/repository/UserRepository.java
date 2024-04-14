package com.example.demo.repository;

import com.example.demo.entities.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, UserClass>{
}
