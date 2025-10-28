package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "BattleParticipants")
public class BattleParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer participantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "battle_id", nullable = false)
    private Battle battle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(columnDefinition = "INT DEFAULT 0")
    private Integer score = 0;

    private Integer rankInBattle;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isWinner = false;

    private Integer timeTakenMs;

    public BattleParticipant(){}

    public BattleParticipant(Integer participantId, Battle battle, User user, Integer score, Integer rankInBattle, Boolean isWinner, Integer timeTakenMs) {
        this.participantId = participantId;
        this.battle = battle;
        this.user = user;
        this.score = score;
        this.rankInBattle = rankInBattle;
        this.isWinner = isWinner;
        this.timeTakenMs = timeTakenMs;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getRankInBattle() {
        return rankInBattle;
    }

    public void setRankInBattle(Integer rankInBattle) {
        this.rankInBattle = rankInBattle;
    }

    public Boolean getWinner() {
        return isWinner;
    }

    public void setWinner(Boolean winner) {
        isWinner = winner;
    }

    public Integer getTimeTakenMs() {
        return timeTakenMs;
    }

    public void setTimeTakenMs(Integer timeTakenMs) {
        this.timeTakenMs = timeTakenMs;
    }

    @Override
    public String toString() {
        return "BattleParticipant{" +
                "participantId=" + participantId +
                ", battle=" + battle +
                ", user=" + user +
                ", score=" + score +
                ", rankInBattle=" + rankInBattle +
                ", isWinner=" + isWinner +
                ", timeTakenMs=" + timeTakenMs +
                '}';
    }
}
