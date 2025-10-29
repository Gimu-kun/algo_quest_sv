package com.example.demo.Controllers;

import com.example.demo.Entity.UserProgress;
import com.example.demo.Services.UserProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/user-progress")
@CrossOrigin("*")
public class UserProgressController {

    @Autowired
    private UserProgressService userProgressService;

    // Tạo mới progress cho user
    @PostMapping("/create/{userId}")
    public ResponseEntity<?> createProgress(@PathVariable Integer userId) {
        Optional<UserProgress> created = userProgressService.createProgressForUser(userId);
        if (created.isEmpty())
            return ResponseEntity.badRequest().body("User không tồn tại hoặc đã có progress!");
        return ResponseEntity.ok(created.get());
    }

    // Lấy toàn bộ progress
    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userProgressService.getAllProgress());
    }

    // Lấy progress theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return userProgressService.getProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Lấy progress theo userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable Integer userId) {
        return userProgressService.getProgressByUserId(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật progress
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UserProgress progress) {
        return userProgressService.updateProgress(id, progress)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Xóa progress
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        boolean deleted = userProgressService.deleteProgress(id);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }
}
