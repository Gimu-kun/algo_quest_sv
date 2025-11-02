package com.example.demo.Dto.Topic;

public class TopicSummaryDto {
    private Integer topicId;
    private String topicName;
    private String description;
    private Integer orderIndex;
    private Integer questCount;

    // Constructor mặc định
    public TopicSummaryDto() {}

    // Constructor đầy đủ
    public TopicSummaryDto(Integer topicId, String topicName, String description, Integer orderIndex, Integer questCount) {
        this.topicId = topicId;
        this.topicName = topicName;
        this.description = description;
        this.orderIndex = orderIndex;
        this.questCount = questCount;
    }

    // Getters and Setters
    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Integer getQuestCount() {
        return questCount;
    }

    public void setQuestCount(Integer questCount) {
        this.questCount = questCount;
    }
}
