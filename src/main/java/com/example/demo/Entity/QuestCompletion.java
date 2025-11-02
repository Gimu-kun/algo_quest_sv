package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "QuestCompletions")
public class QuestCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer completionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("completion_user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quest_id", nullable = false)
    private Quest quest;

    @Column(nullable = false)
    private LocalDateTime completionTime = LocalDateTime.now();

    private Integer score;

    @Column(nullable = false)
    private Integer xpEarned;

    public QuestCompletion(){}

    public QuestCompletion(Integer completionId, User user, Quest quest, LocalDateTime completionTime, Integer score, Integer xpEarned) {
        this.completionId = completionId;
        this.user = user;
        this.quest = quest;
        this.completionTime = completionTime;
        this.score = score;
        this.xpEarned = xpEarned;
    }

    public Integer getCompletionId() {
        return completionId;
    }

    public void setCompletionId(Integer completionId) {
        this.completionId = completionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
    }

    public LocalDateTime getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(LocalDateTime completionTime) {
        this.completionTime = completionTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getXpEarned() {
        return xpEarned;
    }

    public void setXpEarned(Integer xpEarned) {
        this.xpEarned = xpEarned;
    }
}
