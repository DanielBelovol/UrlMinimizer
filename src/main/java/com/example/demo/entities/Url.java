package com.example.demo.entities;


import jakarta.persistence.*;


@Entity
@Table(name = "url_mapping")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "url")
    private String url;

    @Column(name = "short_url")
    private String shortUrl;

    @Column(name = "count_of_calls")
    private int countOfCalls;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    public Url(){
        countOfCalls = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public int getCountOfCalls() {
        return countOfCalls;
    }

    public void setCountOfCalls(int countOfCalls) {
        this.countOfCalls = countOfCalls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
