package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "Answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @JsonBackReference("question-answers")
    private Question question;

    @Lob
    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private Boolean isCorrect;

    private String answerMeta;

    public Answer(){}

    public Answer(Integer answerId, Question question, String answerText, Boolean isCorrect, String answerMeta) {
        this.answerId = answerId;
        this.question = question;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.answerMeta = answerMeta;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getAnswerMeta() {
        return answerMeta;
    }

    public void setAnswerMeta(String answerMeta) {
        this.answerMeta = answerMeta;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", question=" + question +
                ", answerText='" + answerText + '\'' +
                ", isCorrect=" + isCorrect +
                ", answerMeta='" + answerMeta + '\'' +
                '}';
    }
}
