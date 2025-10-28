package com.example.demo.Services;

import com.example.demo.Dto.Answer.AnswerDto;
import com.example.demo.Entity.Answer;
import com.example.demo.Entity.Question;
import com.example.demo.Entity.Quest;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Repository.QuestRepository;
import com.example.demo.Dto.Question.QuestionCreateDto;
import com.example.demo.Dto.Question.QuestionUpdateDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestRepository questRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestRepository questRepository) {
        this.questionRepository = questionRepository;
        this.questRepository = questRepository;
    }

    // Custom Exception Class
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    // CREATE
    public Question createQuestion(QuestionCreateDto createDto) {
        Question question = new Question();
        Integer questId = createDto.getQuestId();
        if (questId != null) {
            Quest questEntity = questRepository.findById(questId)
                    .orElseThrow(() -> new ResourceNotFoundException("Quest not found with id " + questId));

            question.setQuest(questEntity);
        }

        question.setQuestionText(createDto.getQuestionText());
        question.setBloomLevel(createDto.getBloomLevel());
        question.setQuestionType(createDto.getQuestionType());
        question.setCorrectXpReward(createDto.getCorrectXpReward());

        // 2. Thiết lập Answers
        Set<Answer> answers = createDto.getAnswers().stream()
                .map(answerDto -> {
                    Answer answer = new Answer();
                    answer.setAnswerText(answerDto.getAnswerText());
                    answer.setCorrect(answerDto.getIsCorrect());
                    answer.setQuestion(question);
                    return answer;
                })
                .collect(Collectors.toSet());

        question.setAnswers(answers);

        return questionRepository.save(question);
    }

    // READ ALL
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // READ BY ID
    public Optional<Question> getQuestionById(Integer id) {
        // Tùy chọn: Dùng @Transactional và fetch join nếu muốn tải luôn Answers
        return questionRepository.findById(id);
    }

    // UPDATE (Partial Update)
    @Transactional
    public Question updateQuestion(Integer id, QuestionUpdateDto updateDto) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));

        // 1. CẬP NHẬT CÁC TRƯỜNG CỦA QUESTION (Partial Update)
        updateDto.getQuestionText().ifPresent(existingQuestion::setQuestionText);
        updateDto.getBloomLevel().ifPresent(existingQuestion::setBloomLevel);
        updateDto.getQuestionType().ifPresent(existingQuestion::setQuestionType);
        updateDto.getCorrectXpReward().ifPresent(existingQuestion::setCorrectXpReward);

        // 2. TÍCH HỢP CẬP NHẬT/THAY THẾ ANSWERS
        List<AnswerDto> answerDtos = updateDto.getAnswers();

        if (answerDtos != null) {
            // Xóa tất cả các Answers cũ trước (được xử lý bởi cascade hoặc orphanRemoval)
            existingQuestion.getAnswers().clear();

            // Chuyển đổi DTO thành Entity và thiết lập mối quan hệ ngược
            Set<Answer> newAnswers = answerDtos.stream()
                    .map(answerDto -> {
                        Answer answer = new Answer();
                        // Lưu ý: Nếu AnswerDto có ID, bạn có thể tìm và cập nhật Answer cũ
                        // Nhưng để đơn giản và an toàn, ta thay thế toàn bộ (CascadeType.ALL)

                        answer.setAnswerText(answerDto.getAnswerText());
                        answer.setCorrect(answerDto.getIsCorrect());
                        answer.setQuestion(existingQuestion); // Thiết lập liên kết ngược

                        return answer;
                    })
                    .collect(Collectors.toSet());

            // Gán tập hợp mới vào Question
            existingQuestion.setAnswers(newAnswers);
        }

        // 3. LƯU VÀ TRẢ VỀ
        return questionRepository.save(existingQuestion);
    }


    // DELETE
    public void deleteQuestion(Integer id) {
        if (!questionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Question not found with id " + id);
        }
        // Việc xóa Question sẽ gây ra Cascade Delete cho tất cả các Answers liên quan
        questionRepository.deleteById(id);
    }
}
