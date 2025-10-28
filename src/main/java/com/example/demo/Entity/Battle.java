package com.example.demo.Entity;

import com.example.demo.Enums.BattleStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer battleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Column(nullable = false)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('pending', 'in_progress', 'completed', 'canceled')")
    private BattleStatus status;

    private Integer questionSetId;

    @OneToMany(mappedBy = "battle", cascade = CascadeType.ALL)
    private Set<BattleParticipant> participants;

    public Battle(){}

    public Battle(Integer battleId, Topic topic, LocalDateTime startTime, LocalDateTime endTime, BattleStatus status, Integer questionSetId, Set<BattleParticipant> participants) {
        this.battleId = battleId;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.questionSetId = questionSetId;
        this.participants = participants;
    }

    public Integer getBattleId() {
        return battleId;
    }

    public void setBattleId(Integer battleId) {
        this.battleId = battleId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BattleStatus getStatus() {
        return status;
    }

    public void setStatus(BattleStatus status) {
        this.status = status;
    }

    public Integer getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(Integer questionSetId) {
        this.questionSetId = questionSetId;
    }

    public Set<BattleParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<BattleParticipant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Battle{" +
                "battleId=" + battleId +
                ", topic=" + topic +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", questionSetId=" + questionSetId +
                ", participants=" + participants +
                '}';
    }
}
