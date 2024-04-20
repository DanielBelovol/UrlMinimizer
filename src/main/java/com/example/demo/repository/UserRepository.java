package com.example.demo.repository;

import java.util.Optional;
import com.example.demo.entities.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserClass, Long> {

  Optional<UserClass> findByUsername(String username);

}
