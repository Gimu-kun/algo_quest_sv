package com.example.demo.Repository;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProgressRepository extends JpaRepository<UserProgress,Integer> {
    Optional<UserProgress> findByUser_UserId(Integer userId);

    Optional<UserProgress> findByUser(User user);
}
