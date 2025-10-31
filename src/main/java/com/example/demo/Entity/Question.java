package com.example.demo.Entity;

import com.example.demo.Enums.BloomLevel;
import com.example.demo.Enums.QuestionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    @JsonBackReference("quest-question")
    private Quest quest;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer orderIndex = 0;

    @Lob
    @Column(nullable = false)
    private String questionText;

    @Enumerated(EnumType.STRING)
    private BloomLevel bloomLevel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionType questionType;

    @Column(nullable = false)
    private Integer correctXpReward;

    @Column(name = "partial_credit")
    private Integer partialCredit;

    @Lob
    @Column(name = "synonyms", columnDefinition = "TEXT")
    private String synonyms;

    @Lob
    @Column(name = "code_template")
    private String codeTemplate;

    @Lob
    @Column(name = "test_cases")
    private String testCases;

    @Lob
    @Column(name = "test_results")
    private String testResults;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @JsonManagedReference("question-answers")
    private Set<Answer> answers;

    public Question(){}

    public Question(Integer questionId, Quest quest, String questionText, BloomLevel bloomLevel, QuestionType questionType, Integer correctXpReward, Integer partialCredit, String synonyms, String codeTemplate, String testCases, String testResults, Set<Answer> answers) {
        this.questionId = questionId;
        this.quest = quest;
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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Quest getQuest() {
        return quest;
    }

    public void setQuest(Quest quest) {
        this.quest = quest;
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

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", quest=" + quest +
                ", questionText='" + questionText + '\'' +
                ", bloomLevel=" + bloomLevel +
                ", questionType=" + questionType +
                ", correctXpReward=" + correctXpReward +
                ", partialCredit=" + partialCredit +
                ", synonyms='" + synonyms + '\'' +
                ", codeTemplate='" + codeTemplate + '\'' +
                ", testCases='" + testCases + '\'' +
                ", testResults='" + testResults + '\'' +
                ", answers=" + answers +
                '}';
    }
}
