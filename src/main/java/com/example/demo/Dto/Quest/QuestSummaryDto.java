package com.example.demo.Dto.Quest;

public class QuestSummaryDto {
    private Integer questId;
    private String questName;
    private Integer orderIndex;
    private String questType;
    private String difficulty;
    private Integer requiredXp;

    public QuestSummaryDto() {}

    public QuestSummaryDto(Integer questId, String questName, Integer orderIndex, String questType, String difficulty, Integer requiredXp) {
        this.questId = questId;
        this.questName = questName;
        this.orderIndex = orderIndex;
        this.questType = questType;
        this.difficulty = difficulty;
        this.requiredXp = requiredXp;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getQuestType() {
        return questType;
    }

    public void setQuestType(String questType) {
        this.questType = questType;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(Integer requiredXp) {
        this.requiredXp = requiredXp;
    }
}