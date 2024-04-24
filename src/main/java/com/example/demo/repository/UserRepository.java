package com.example.demo.repository;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;

import com.example.demo.entities.UrlClass;
import com.example.demo.entities.UserClass;
=======
import com.example.demo.entities.User;
>>>>>>> origin/thymeleaf
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<UserClass, Long> {

  @Query("SELECT u FROM UserClass u WHERE u.username = :usernameOrEmail OR u.email = :usernameOrEmail")
  Optional<UserClass> findByUsernameOrEmail(String usernameOrEmail);

  UserClass findByUsername(String username);
=======
public interface UserRepository extends JpaRepository<User, Long> {
>>>>>>> origin/thymeleaf
}
