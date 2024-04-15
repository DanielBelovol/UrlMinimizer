package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "username", nullable = false)
  private String username;

  @Email
  @NotEmpty
  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "is_admin", nullable = false)
  private boolean isAdmin;
}
