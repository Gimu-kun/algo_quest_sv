package com.example.demo.Dto.Answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AnswerDto {

    private Integer answerId;

    @NotBlank
    private String answerText;

    @NotNull
    private Boolean isCorrect;

    public AnswerDto() {
    }

    public AnswerDto(Integer answerId, String answerText, Boolean isCorrect) {
        this.answerId = answerId;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean correct) {
        isCorrect = correct;
    }
}