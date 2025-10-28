package com.example.demo.Dto.Question;

import com.example.demo.Dto.Answer.AnswerDto;
import com.example.demo.Enums.BloomLevel;
import com.example.demo.Enums.QuestionType;

import java.util.List;
import java.util.Optional;

public class QuestionUpdateDto {
    private Optional<String> questionText = Optional.empty();
    private Optional<BloomLevel> bloomLevel = Optional.empty();
    private Optional<QuestionType> questionType = Optional.empty();
    private Optional<Integer> correctXpReward = Optional.empty();
    private List<AnswerDto> answers;

    public QuestionUpdateDto(){}

    public QuestionUpdateDto(Optional<String> questionText, Optional<BloomLevel> bloomLevel, Optional<QuestionType> questionType, Optional<Integer> correctXpReward) {
        this.questionText = questionText;
        this.bloomLevel = bloomLevel;
        this.questionType = questionType;
        this.correctXpReward = correctXpReward;
    }

    public Optional<String> getQuestionText() {
        return questionText;
    }

    public void setQuestionText(Optional<String> questionText) {
        this.questionText = questionText;
    }

    public Optional<BloomLevel> getBloomLevel() {
        return bloomLevel;
    }

    public void setBloomLevel(Optional<BloomLevel> bloomLevel) {
        this.bloomLevel = bloomLevel;
    }

    public Optional<QuestionType> getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Optional<QuestionType> questionType) {
        this.questionType = questionType;
    }

    public Optional<Integer> getCorrectXpReward() {
        return correctXpReward;
    }

    public void setCorrectXpReward(Optional<Integer> correctXpReward) {
        this.correctXpReward = correctXpReward;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
