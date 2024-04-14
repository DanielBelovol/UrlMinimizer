package com.example.demo.repository;

<<<<<<< HEAD
import com.example.demo.entities.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Long, UserClass>{
=======
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
>>>>>>> 5a51901301a22f09a0970e4232f4eab4f5dbc2c1
}
