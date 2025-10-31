package com.example.demo.Dto.Quest;

public class QuestProgressDto {
    private Integer questId;
    private String questName;
    private Integer totalItems;      // Tổng số câu hỏi/nhiệm vụ trong chủ đề
    private Integer completedItems;  // Số lượng đã hoàn thành
    private Double completionPercentage; // Phần trăm tiến độ (completedItems / totalItems) * 100

    public QuestProgressDto() {}

    public QuestProgressDto(Integer questId, String questName, Integer totalItems, Integer completedItems) {
        this.questId = questId;
        this.questName = questName;
        this.totalItems = totalItems;
        this.completedItems = completedItems;
        this.completionPercentage = (totalItems > 0) ? ((double) completedItems / totalItems) * 100 : 0.0;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getCompletedItems() {
        return completedItems;
    }

    public void setCompletedItems(Integer completedItems) {
        this.completedItems = completedItems;
    }

    public void setCompletionPercentage(Double completionPercentage) {
        this.completionPercentage = completionPercentage;
    }

    public Double getCompletionPercentage() {
        return (totalItems != null && totalItems > 0) ? ((double) completedItems / totalItems) * 100 : 0.0;
    }
}
