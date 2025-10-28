package com.example.demo.Controllers;

import com.example.demo.Entity.UserRating;
import com.example.demo.Repository.UserRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class UserRatingController {

    @Autowired
    private UserRatingRepository userRatingRepository;

    @GetMapping("/{userId}")
    public ResponseEntity<UserRating> getUserRating(@PathVariable Integer userId) {
        UserRating rating = userRatingRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("User rating not found"));
        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public List<UserRating> getAllRatings() {
        return userRatingRepository.findAll();
    }
}

