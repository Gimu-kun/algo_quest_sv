package com.example.demo.Controllers;

import com.example.demo.Dto.Badge.IBadgeUpdateDto;
import com.example.demo.Entity.Badge;
import com.example.demo.Services.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badges")
@CrossOrigin("*")
public class BadgeController {

    private final BadgeService badgeService;

    @Autowired
    public BadgeController(BadgeService badgeService) {
        this.badgeService = badgeService;
    }

    // Tạo huy hiệu mới
    @PostMapping
    public ResponseEntity<Badge> createBadge(@RequestBody Badge badge) {
        try {
            Badge newBadge = badgeService.createBadge(badge);
            return new ResponseEntity<>(newBadge, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            // Xử lý lỗi trùng tên huy hiệu
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    // Lấy tất cả huy hiệu
    @GetMapping
    public ResponseEntity<List<Badge>> getAllBadges() {
        List<Badge> badges = badgeService.getAllBadges();
        return ResponseEntity.ok(badges);
    }

    // Lấy huy hiệu theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Badge> getBadgeById(@PathVariable Integer id) {
        return badgeService.getBadgeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật huy hiệu
    @PutMapping("/{id}")
    public ResponseEntity<Badge> updateBadge(@PathVariable Integer id, @RequestBody IBadgeUpdateDto badgeDetails) {
        try {
            Badge updatedBadge = badgeService.updateBadge(id, badgeDetails);
            return ResponseEntity.ok(updatedBadge);
        } catch (BadgeService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa huy hiệu
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBadge(@PathVariable Integer id) {
        try {
            badgeService.deleteBadge(id);
            return ResponseEntity.noContent().build();
        } catch (BadgeService.ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
