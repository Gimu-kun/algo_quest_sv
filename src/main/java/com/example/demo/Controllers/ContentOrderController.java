package com.example.demo.Controllers;

import com.example.demo.Dto.OrderUpdateRequest;
import com.example.demo.Entity.Quest;
import com.example.demo.Entity.Question;
import com.example.demo.Services.ContentOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content/order")
@Tag(name = "Content Order API", description = "API để điều chỉnh thứ tự hiển thị của Quest và Question")
@CrossOrigin("*")
public class ContentOrderController {

    @Autowired
    private ContentOrderService contentOrderService;

    @Operation(summary = "Điều chỉnh thứ tự của các Quest trong một Topic")
    @PutMapping("/quests")
    public ResponseEntity<List<Quest>> updateQuestOrder(@RequestBody OrderUpdateRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Quest> updatedQuests = contentOrderService.updateQuestOrder(request);
        return ResponseEntity.ok(updatedQuests);
    }

    @Operation(summary = "Điều chỉnh thứ tự của các Question trong một Quest")
    @PutMapping("/questions")
    public ResponseEntity<List<Question>> updateQuestionOrder(@RequestBody OrderUpdateRequest request) {
        if (request.getItems() == null || request.getItems().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Question> updatedQuestions = contentOrderService.updateQuestionOrder(request);
        return ResponseEntity.ok(updatedQuestions);
    }
}