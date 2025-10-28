package com.example.demo.Controllers;

import com.example.demo.Entity.Topic;
import com.example.demo.Services.TopicService;
import com.example.demo.Dto.Topic.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    // POST: /api/topics
    // CREATE: Tạo chủ đề mới
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        try {
            Topic newTopic = topicService.createTopic(topic);
            return new ResponseEntity<>(newTopic, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Xử lý lỗi trùng tên
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // GET: /api/topics
    // READ ALL: Lấy tất cả chủ đề
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    // GET: /api/topics/{id}
    // READ BY ID: Lấy chủ đề theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Integer id) {
        return topicService.getTopicById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT: /api/topics/{id}
    // UPDATE: Cập nhật chủ đề (sử dụng DTO cho Partial Update)
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Integer id, @RequestBody TopicUpdateDto updateDto) {
        try {
            Topic updatedTopic = topicService.updateTopic(id, updateDto);
            return ResponseEntity.ok(updatedTopic);
        } catch (TopicService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: /api/topics/{id}
    // DELETE: Xóa chủ đề
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Integer id) {
        try {
            topicService.deleteTopic(id);
            return ResponseEntity.noContent().build();
        } catch (TopicService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
