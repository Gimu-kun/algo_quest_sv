package com.example.demo.Dto.Topic;

public class TopicProgressDto {
    private String topicName;
    private Integer totalQuests;
    private Integer completedQuests;
    private Double completionPercentage; // Tiến độ Topic

    // Constructor
    public TopicProgressDto(String topicName, Integer totalQuests, Integer completedQuests) {
        this.topicName = topicName;
        this.totalQuests = totalQuests;
        this.completedQuests = completedQuests;
        this.completionPercentage = totalQuests > 0 ? (double) completedQuests / totalQuests * 100 : 0.0;
    }

    // Getters
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
    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setTotalQuests(Integer totalQuests) {
        this.totalQuests = totalQuests;
        this.completionPercentage = totalQuests > 0 ? (double) this.completedQuests / totalQuests * 100 : 0.0;
    }

    public void setCompletedQuests(Integer completedQuests) {
        this.completedQuests = completedQuests;
        this.completionPercentage = this.totalQuests > 0 ? (double) completedQuests / this.totalQuests * 100 : 0.0;
    }
}
