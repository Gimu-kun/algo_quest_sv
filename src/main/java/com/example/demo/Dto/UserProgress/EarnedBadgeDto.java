package com.example.demo.Dto.UserProgress;

import java.time.LocalDateTime;

public class EarnedBadgeDto {

    private Integer badgeId;
    private String badgeName;
    private String description;
    private String imageUrl;
    private LocalDateTime awardedAt;

    // Constructor mặc định
    public EarnedBadgeDto() {
    }

    // Constructor đầy đủ tham số
    public EarnedBadgeDto(Integer badgeId, String badgeName, String description, String imageUrl, LocalDateTime awardedAt) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.awardedAt = awardedAt;
    }

    // Getters
    public Integer getBadgeId() {
        return badgeId;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LocalDateTime getAwardedAt() {
        return awardedAt;
    }

    // Setters
    public void setBadgeId(Integer badgeId) {
        this.badgeId = badgeId;
    }

    public void setBadgeName(String badgeName) {
        this.badgeName = badgeName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAwardedAt(LocalDateTime awardedAt) {
        this.awardedAt = awardedAt;
    }
}
