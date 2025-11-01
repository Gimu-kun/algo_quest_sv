package com.example.demo.Dto.Question;

import com.example.demo.Dto.Answer.AnswerDto;
import com.example.demo.Dto.Quest.QuestNameProjection;
import com.example.demo.Entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionResponseDto {
    private Integer questionId;
    private String questionText;
    private String bloomLevel;
    private String questionType;
    private Integer correctXpReward;

    // --- TRƯỜNG PHỤ MỚI ---
    private Integer partialCredit;
    private String synonyms;
    private String codeTemplate;
    private String testCases;
    private String testResults;
    // ----------------------

    private List<AnswerDto> answers;
    private QuestNameProjection quest;
    private Integer orderIndex;

    public QuestionResponseDto() {
    }

    public QuestionResponseDto(Question question, QuestNameProjection questProjection) {
        this.questionId = question.getQuestionId();
        this.questionText = question.getQuestionText();
        this.bloomLevel = question.getBloomLevel().name().toLowerCase();
        this.questionType = question.getQuestionType().name().toLowerCase();
        this.correctXpReward = question.getCorrectXpReward();
        this.partialCredit = question.getPartialCredit();
        this.synonyms = question.getSynonyms();
        this.codeTemplate = question.getCodeTemplate();
        this.testCases = question.getTestCases();
        this.testResults = question.getTestResults();
        this.orderIndex = question.getOrderIndex();

        // Map Answers sang AnswerDto
        this.answers = question.getAnswers().stream()
                .map(answer -> new AnswerDto(answer.getAnswerId(), answer.getAnswerText(), answer.getCorrect(), answer.getAnswerMeta()))
                .collect(Collectors.toList());

        this.quest = questProjection;
    }

    // Constructor phụ (ví dụ cho trường hợp answers đã được fetch riêng)
    public QuestionResponseDto(Question question, QuestNameProjection questProjection, List<AnswerDto> answers) {
        this.questionId = question.getQuestionId();
        this.questionText = question.getQuestionText();
        this.bloomLevel = question.getBloomLevel().name().toLowerCase();
        this.questionType = question.getQuestionType().name().toLowerCase();
        this.correctXpReward = question.getCorrectXpReward();

        // Map các trường phụ mới từ Entity
        this.partialCredit = question.getPartialCredit();
        this.synonyms = question.getSynonyms();
        this.codeTemplate = question.getCodeTemplate();
        this.testCases = question.getTestCases();
        this.testResults = question.getTestResults();

        this.answers = answers;
        this.quest = questProjection;
    }

    // --- Getters and Setters ---

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

    public Integer getPartialCredit() {
        return partialCredit;
    }

    public void setPartialCredit(Integer partialCredit) {
        this.partialCredit = partialCredit;
    }

    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
    }

    public String getCodeTemplate() {
        return codeTemplate;
    }

    public void setCodeTemplate(String codeTemplate) {
        this.codeTemplate = codeTemplate;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }

    public String getTestResults() {
        return testResults;
    }

    public void setTestResults(String testResults) {
        this.testResults = testResults;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }
}
