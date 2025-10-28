package com.example.demo.Dto.Question;

import com.example.demo.Dto.Answer.AnswerDto;
import com.example.demo.Enums.BloomLevel;
import com.example.demo.Enums.QuestionType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class QuestionCreateDto {

    private Integer questId = null;

    @NotNull
    private String questionText;

    private BloomLevel bloomLevel;

    @NotNull
    private QuestionType questionType;

    @NotNull
    private Integer correctXpReward;

    @NotEmpty
    private List<AnswerDto> answers;

    public QuestionCreateDto(){}

    public QuestionCreateDto(Integer questId, String questionText, BloomLevel bloomLevel, QuestionType questionType, Integer correctXpReward, List<AnswerDto> answers) {
        this.questId = questId;
        this.questionText = questionText;
        this.bloomLevel = bloomLevel;
        this.questionType = questionType;
        this.correctXpReward = correctXpReward;
        this.answers = answers;
    }

    public Integer getQuestId() {
        return questId;
    }

    public void setQuestId(Integer questId) {
        this.questId = questId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public BloomLevel getBloomLevel() {
        return bloomLevel;
    }

    public void setBloomLevel(BloomLevel bloomLevel) {
        this.bloomLevel = bloomLevel;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Integer getCorrectXpReward() {
        return correctXpReward;
    }

    public void setCorrectXpReward(Integer correctXpReward) {
        this.correctXpReward = correctXpReward;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
