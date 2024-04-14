package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.lang.Long;
@Entity
@Data
@Table(name = "users")
public class UserClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;

}
