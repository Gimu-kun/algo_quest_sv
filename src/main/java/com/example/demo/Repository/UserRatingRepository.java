package com.example.demo.Repository;

import com.example.demo.Entity.UserRating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRatingRepository extends JpaRepository<UserRating,Integer> {
    Optional<UserRating> findByUser_UserId(Integer userId);
}
