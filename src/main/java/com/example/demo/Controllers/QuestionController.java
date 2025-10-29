package com.example.demo.Controllers;

import com.example.demo.Dto.Question.QuestionResponseDto;
import com.example.demo.Entity.Question;
import com.example.demo.Services.QuestionService;
import com.example.demo.Dto.Question.QuestionCreateDto;
import com.example.demo.Dto.Question.QuestionUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin("*")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // POST: /api/questions
    // CREATE: Tạo câu hỏi mới
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateDto createDto) {
        try {
            Question newQuestion = questionService.createQuestion(createDto);
            return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
        } catch (QuestionService.ResourceNotFoundException e) {
            // Xử lý khi questId không tồn tại
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // GET: /api/questions
    // READ ALL: Lấy tất cả câu hỏi
    @GetMapping
    public ResponseEntity<List<QuestionResponseDto>> getAllQuestions() {
        List<QuestionResponseDto> questions = questionService.getAllQuestions(); // <-- Gọi hàm mới
        return ResponseEntity.ok(questions);
    }

    // GET: /api/questions/{id}
    // READ BY ID: Lấy câu hỏi theo ID (cũng hiển thị Answers nhờ @JsonManagedReference)
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> getQuestionById(@PathVariable Integer id) { // <-- Thay đổi kiểu trả về
        return questionService.getQuestionDtoById(id) // <-- Thay đổi tên phương thức gọi
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: /api/questions/{id}
    // UPDATE: Cập nhật câu hỏi (Partial Update)
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody QuestionUpdateDto updateDto) {
        try {
            Question updatedQuestion = questionService.updateQuestion(id, updateDto);
            return ResponseEntity.ok(updatedQuestion);
        } catch (QuestionService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: /api/questions/{id}
    // DELETE: Xóa câu hỏi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (QuestionService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
