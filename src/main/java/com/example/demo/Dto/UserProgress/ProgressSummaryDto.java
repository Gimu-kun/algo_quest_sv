package com.example.demo.Dto.UserProgress;

public class ProgressSummaryDto {

    private Integer totalXp;
    private Integer currentLevel;
    private Integer questsCompletedCount;
    private Integer badgesEarnedCount;

    // Constructor mặc định
    public ProgressSummaryDto() {
    }

    // Constructor đầy đủ tham số
    public ProgressSummaryDto(Integer totalXp, Integer currentLevel, Integer questsCompletedCount, Integer badgesEarnedCount) {
        this.totalXp = totalXp;
        this.currentLevel = currentLevel;
        this.questsCompletedCount = questsCompletedCount;
        this.badgesEarnedCount = badgesEarnedCount;
    }

    // Getters
    public Integer getTotalXp() {
        return totalXp;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public Integer getQuestsCompletedCount() {
        return questsCompletedCount;
    }

    public Integer getBadgesEarnedCount() {
        return badgesEarnedCount;
    }

    // Setters
    public void setTotalXp(Integer totalXp) {
        this.totalXp = totalXp;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void setQuestsCompletedCount(Integer questsCompletedCount) {
        this.questsCompletedCount = questsCompletedCount;
    }

    public void setBadgesEarnedCount(Integer badgesEarnedCount) {
        this.badgesEarnedCount = badgesEarnedCount;
    }
}
