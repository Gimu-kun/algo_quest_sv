package com.example.demo.Dto.Question;

import com.example.demo.Dto.Answer.AnswerDto;
import com.example.demo.Dto.Quest.QuestNameProjection;
import com.example.demo.Entity.Question;

import java.util.List;

public class QuestionResponseDto {
    private Integer questionId;
    private String questionText;
    private String bloomLevel;
    private String questionType;
    private Integer correctXpReward;
    private List<AnswerDto> answers;
    private QuestNameProjection quest; // Sử dụng Projection

    public QuestionResponseDto() {
    }

    public QuestionResponseDto(Question question, QuestNameProjection questProjection) {
        this.questionId = question.getQuestionId();
        this.questionText = question.getQuestionText();
        this.bloomLevel = question.getBloomLevel().name().toLowerCase();
        this.questionType = question.getQuestionType().name().toLowerCase();
        this.correctXpReward = question.getCorrectXpReward();

        // Map Answers sang AnswerDto
        this.answers = question.getAnswers().stream()
                .map(answer -> new AnswerDto(answer.getAnswerId(), answer.getAnswerText(), answer.getCorrect(), answer.getAnswerMeta()))
                .collect(java.util.stream.Collectors.toList());

        this.quest = questProjection;
    }

    public QuestionResponseDto(Question question, QuestNameProjection questProjection, List<AnswerDto> answers) {
        this.questionId = question.getQuestionId();
        this.questionText = question.getQuestionText();
        this.bloomLevel = question.getBloomLevel().name().toLowerCase(); // Chuyển Enum sang String
        this.questionType = question.getQuestionType().name().toLowerCase();
        this.correctXpReward = question.getCorrectXpReward();
        this.answers = answers;
        this.quest = questProjection;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getBloomLevel() {
        return bloomLevel;
    }

    public void setBloomLevel(String bloomLevel) {
        this.bloomLevel = bloomLevel;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
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

    public QuestNameProjection getQuest() {
        return quest;
    }

    public void setQuest(QuestNameProjection quest) {
        this.quest = quest;
    }
}