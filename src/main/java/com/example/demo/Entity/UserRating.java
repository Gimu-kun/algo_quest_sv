package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "UserRatings")
public class UserRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonManagedReference("user-rating-entry")
    private User user;

    @Column(columnDefinition = "INT DEFAULT 1000")
    private Integer eloRating = 1000;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer winCount = 0;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer lossCount = 0;

    private LocalDateTime lastPlayed;

    public UserRating(){}

    public UserRating(Integer ratingId, User user, Integer eloRating, Integer winCount, Integer lossCount, LocalDateTime lastPlayed) {
        this.ratingId = ratingId;
        this.user = user;
        this.eloRating = eloRating;
        this.winCount = winCount;
        this.lossCount = lossCount;
        this.lastPlayed = lastPlayed;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getEloRating() {
        return eloRating;
    }

    public void setEloRating(Integer eloRating) {
        this.eloRating = eloRating;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public void setWinCount(Integer winCount) {
        this.winCount = winCount;
    }

    public Integer getLossCount() {
        return lossCount;
    }

    public void setLossCount(Integer lossCount) {
        this.lossCount = lossCount;
    }

    public LocalDateTime getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(LocalDateTime lastPlayed) {
        this.lastPlayed = lastPlayed;
    }
}
