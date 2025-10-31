package com.example.demo.Dto.UserProgress;

import com.example.demo.Entity.User;

import java.util.List;

public class UserProgressDetailDto {
    // Thông tin cơ bản của người dùng
    private Integer userId;
    private String username;
    private String fullName;

    // Tổng hợp tiến độ chính (Summary)
    private ProgressSummaryDto progressSummary;

    // Tỉ lệ hoàn thành theo Topic
    private List<TopicCompletionDto> topicCompletionRates;

    // Danh sách huy hiệu đã đạt được
    private List<EarnedBadgeDto> earnedBadges;

    public UserProgressDetailDto(User user, ProgressSummaryDto summary,
                                List<TopicCompletionDto> topicRates,
                                List<EarnedBadgeDto> badges) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.fullName = user.getFullName(); // Cần thêm fullName vào User Entity
        this.progressSummary = summary;
        this.topicCompletionRates = topicRates;
        this.earnedBadges = badges;
    }

    public UserProgressDetailDto(Integer userId, String username, String fullName, ProgressSummaryDto progressSummary, List<TopicCompletionDto> topicCompletionRates, List<EarnedBadgeDto> earnedBadges) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.progressSummary = progressSummary;
        this.topicCompletionRates = topicCompletionRates;
        this.earnedBadges = earnedBadges;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ProgressSummaryDto getProgressSummary() {
        return progressSummary;
    }

    public void setProgressSummary(ProgressSummaryDto progressSummary) {
        this.progressSummary = progressSummary;
    }

    public List<TopicCompletionDto> getTopicCompletionRates() {
        return topicCompletionRates;
    }

    public void setTopicCompletionRates(List<TopicCompletionDto> topicCompletionRates) {
        this.topicCompletionRates = topicCompletionRates;
    }

    public List<EarnedBadgeDto> getEarnedBadges() {
        return earnedBadges;
    }

    public void setEarnedBadges(List<EarnedBadgeDto> earnedBadges) {
        this.earnedBadges = earnedBadges;
    }
}