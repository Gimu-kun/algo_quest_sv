package com.example.demo.Dto.Badge;

import java.util.Optional;

public class IBadgeUpdateDto {
    private Optional<String> badgeName = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> imageUrl = Optional.empty();

    public IBadgeUpdateDto(){}

    public IBadgeUpdateDto(Optional<String> badgeName, Optional<String> description, Optional<String> imageUrl) {
        this.badgeName = badgeName;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public Optional<String> getBadgeName() {
        return badgeName;
    }

    public void setBadgeName(Optional<String> badgeName) {
        this.badgeName = badgeName;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Optional<String> imageUrl) {
        this.imageUrl = imageUrl;
    }
}
