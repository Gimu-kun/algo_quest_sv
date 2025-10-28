package com.example.demo.Entity;

import com.example.demo.Enums.Difficulty;
import com.example.demo.Enums.QuestType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Quests")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonBackReference("topic-quest")
    private Topic topic;

    @Column(nullable = false, length = 100)
    private String questName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('quiz', 'puzzle')")
    private QuestType questType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('easy', 'medium', 'hard')")
    private Difficulty difficulty;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer requiredXp = 0;

    @OneToMany(mappedBy = "quest", cascade = CascadeType.ALL)
    @JsonManagedReference("quest-question")
    private Set<Question> questions;

    public Quest(){}

    public Quest(Integer questId, Topic topic, String questName, QuestType questType, Difficulty difficulty, Integer requiredXp, Set<Question> questions) {
        this.questId = questId;
        this.topic = topic;
        this.questName = questName;
        this.questType = questType;
        this.difficulty = difficulty;
        this.requiredXp = requiredXp;
        this.questions = questions;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public QuestType getQuestType() {
        return questType;
    }

    public void setQuestType(QuestType questType) {
        this.questType = questType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getRequiredXp() {
        return requiredXp;
    }

    public void setRequiredXp(Integer requiredXp) {
        this.requiredXp = requiredXp;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
