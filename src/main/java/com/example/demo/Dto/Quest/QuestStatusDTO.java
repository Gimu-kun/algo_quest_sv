package com.example.demo.Dto.Quest;

import com.example.demo.Enums.Difficulty;
import com.example.demo.Enums.QuestType;
import com.example.demo.Enums.QuestionType;

public class QuestStatusDTO {
    private Integer questId;
    private String questName;
    private Integer orderIndex;
    private Boolean isCompleted;
    private Boolean isLocked;
    private Difficulty difficulty;
    private QuestType questType;
    private Integer requiredXp;

    public QuestStatusDTO() {}

    public QuestStatusDTO(Integer questId, String questName, Integer orderIndex, Boolean isCompleted, Boolean isLocked, Difficulty difficulty,QuestType questType,Integer requiredXp) {
        this.questId = questId;
        this.questName = questName;
        this.orderIndex = orderIndex;
        this.isCompleted = isCompleted;
        this.isLocked = isLocked;
        this.difficulty = difficulty;
        this.questType = questType;
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

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public Integer getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(Integer requiredXp) {
        this.requiredXp = requiredXp;
    }
}