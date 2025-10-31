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

    // --- TRƯỜNG PHỤ MỚI (Dùng Optional để cập nhật một phần) ---
    private Optional<Integer> partialCredit = Optional.empty();
    private Optional<String> synonyms = Optional.empty();
    private Optional<String> codeTemplate = Optional.empty();
    private Optional<String> testCases = Optional.empty();
    private Optional<String> testResults = Optional.empty();
    // -----------------------------------------------------------

    private List<AnswerDto> answers;

    public QuestionUpdateDto(){}

    public QuestionUpdateDto(Optional<String> questionText, Optional<BloomLevel> bloomLevel, Optional<QuestionType> questionType, Optional<Integer> correctXpReward, Optional<Integer> partialCredit, Optional<String> synonyms, Optional<String> codeTemplate, Optional<String> testCases, Optional<String> testResults, List<AnswerDto> answers) {
        this.questionText = questionText;
        this.bloomLevel = bloomLevel;
        this.questionType = questionType;
        this.correctXpReward = correctXpReward;
        this.partialCredit = partialCredit;
        this.synonyms = synonyms;
        this.codeTemplate = codeTemplate;
        this.testCases = testCases;
        this.testResults = testResults;
        this.answers = answers;
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

    public Optional<Integer> getPartialCredit() {
        return partialCredit;
    }

    public void setPartialCredit(Optional<Integer> partialCredit) {
        this.partialCredit = partialCredit;
    }

    public Optional<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Optional<String> synonyms) {
        this.synonyms = synonyms;
    }

    public Optional<String> getCodeTemplate() {
        return codeTemplate;
    }

    public void setCodeTemplate(Optional<String> codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    public Optional<String> getTestCases() {
        return testCases;
    }

    public void setTestCases(Optional<String> testCases) {
        this.testCases = testCases;
    }

    public Optional<String> getTestResults() {
        return testResults;
    }

    public void setTestResults(Optional<String> testResults) {
        this.testResults = testResults;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }
}
