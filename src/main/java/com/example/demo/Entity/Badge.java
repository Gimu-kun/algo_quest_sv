package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Badges")
public class Badge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer badgeId;

    @Column(unique = true, nullable = false, length = 100)
    private String badgeName;

    @Lob
    private String description;

    @Column(length = 255)
    private String imageUrl;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    @JsonManagedReference("badge-holders")
    private Set<UserBadge> userBadges = Set.of();

    public Badge(){}

    public Badge(Integer badgeId, String badgeName, String description, String imageUrl, Set<UserBadge> userBadges) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.userBadges = userBadges;
    }

    public Integer getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<UserBadge> getUserBadges() {
        return userBadges;
    }

    public void setUserBadges(Set<UserBadge> userBadges) {
        this.userBadges = userBadges;
    }

    @Override
    public String toString() {
        return "Badge{" +
                "badgeId=" + badgeId +
                ", badgeName='" + badgeName + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", userBadges=" + userBadges +
                '}';
    }
}
