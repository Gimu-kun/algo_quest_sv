package com.example.demo.Entity;

import com.example.demo.Enums.UserRoleEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String fullName;

    @Lob
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('player', 'admin') DEFAULT 'player'")
    private UserRoleEnum role = UserRoleEnum.player;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-progress")
    private UserProgress userProgress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-badges")
    private Set<UserBadge> userBadges;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference("user-battle-entry")
    private Set<BattleParticipant> battleParticipants;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference("user-rating-entry")
    private UserRating userRating;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference("completion_user")
    private List<QuestCompletion> completions;

    public User(){}

    public User(Integer userId, String username, String passwordHash, String email, String fullName, String avatar, UserRoleEnum role, LocalDateTime createdAt, UserProgress userProgress, Set<UserBadge> userBadges) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.fullName = fullName;
        this.avatar = avatar;
        this.role = role;
        this.createdAt = createdAt;
        this.userProgress = userProgress;
        this.userBadges = userBadges;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserProgress getUserProgress() {
        return userProgress;
    }

    public void setUserProgress(UserProgress userProgress) {
        this.userProgress = userProgress;
    }

    public Set<UserBadge> getUserBadges() {
        return userBadges;
    }

    public void setUserBadges(Set<UserBadge> userBadges) {
        this.userBadges = userBadges;
    }
}
