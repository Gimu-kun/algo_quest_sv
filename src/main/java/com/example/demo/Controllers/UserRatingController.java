package com.example.demo.Controllers;

import com.example.demo.Entity.UserRating;
import com.example.demo.Repository.UserRatingRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@CrossOrigin("*")
public class UserRatingController {

    @Autowired
    private UserRatingRepository userRatingRepository;

    @Operation(summary = "Lấy Rating của một người dùng theo ID")
    @GetMapping("/{userId}")
    public ResponseEntity<UserRating> getUserRating(@PathVariable Integer userId) {
        UserRating rating = userRatingRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("User rating not found"));
        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public Page<UserRating> getAllRatings(Pageable pageable) {
        return userRatingRepository.findAll(pageable);
    }
}

