package com.example.demo.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "links")
public class UrlClass {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "link_id")
  private Long linkId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id")
  private UserClass user;

  @Column(name = "original_url", nullable = false, length = 2000)
  private String originalUrl;

  @Column(name = "short_url", nullable = false)
  private String shortUrl;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @PrePersist
  private void prePersist() {
    creationDate = LocalDateTime.now();
  }
}
