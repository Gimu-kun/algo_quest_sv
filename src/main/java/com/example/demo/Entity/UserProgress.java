package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Userprogress")
public class UserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer progressId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference("user-progress")
    private User user;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer totalXp = 0;

    @Column(columnDefinition = "INT DEFAULT 1")
    private Integer currentLevel = 1;

    private LocalDateTime lastUpdated;

    @PreUpdate
    @PrePersist
    public void updateLastUpdated() {
        this.lastUpdated = LocalDateTime.now();
    }

    public UserProgress() {}

    public UserProgress(Integer progressId, User user, Integer totalXp, Integer currentLevel, LocalDateTime lastUpdated) {
        this.progressId = progressId;
        this.user = user;
        this.totalXp = totalXp;
        this.currentLevel = currentLevel;
        this.lastUpdated = lastUpdated;
    }

    public Integer getProgressId() {
        return progressId;
    }

    public void setProgressId(Integer progressId) {
        this.progressId = progressId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(Integer totalXp) {
        this.totalXp = totalXp;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
