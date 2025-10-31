package com.example.demo.Dto.UserProgress;

public class TopicCompletionDto {

    private Integer topicId;
    private String topicName;
    private Integer totalQuests;
    private Integer completedQuests;
    private Double completionPercentage;

    // Constructor mặc định
    public TopicCompletionDto() {
    }

    // Constructor đầy đủ tham số
    public TopicCompletionDto(Integer topicId, String topicName, Integer totalQuests, Integer completedQuests, Double completionPercentage) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.totalQuests = totalQuests;
        this.completedQuests = completedQuests;
        this.completionPercentage = completionPercentage;
    }

    // Getters
    public Integer getTopicId() {
        return topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public Integer getTotalQuests() {
        return totalQuests;
    }

    public Integer getCompletedQuests() {
        return completedQuests;
    }

    public Double getCompletionPercentage() {
        return completionPercentage;
    }

    // Setters
    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTotalQuests(Integer totalQuests) {
        this.totalQuests = totalQuests;
    }

    public void setCompletedQuests(Integer completedQuests) {
        this.completedQuests = completedQuests;
    }

    public void setCompletionPercentage(Double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }
}
