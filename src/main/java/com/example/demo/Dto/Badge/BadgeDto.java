package com.example.demo.Dto.Badge;

public class BadgeDto {
    private Integer badgeId;
    private String badgeName;
    private String description;
    private String imageUrl;

    public BadgeDto() {}

    // Constructor đầy đủ
    public BadgeDto(Integer badgeId, String badgeName, String description, String imageUrl) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    // Getters và Setters
    public Integer getBadgeId() { return badgeId; }
    public void setBadgeId(Integer badgeId) { this.badgeId = badgeId; }
    public String getBadgeName() { return badgeName; }
    public void setBadgeName(String badgeName) { this.badgeName = badgeName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
