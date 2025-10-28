package com.example.demo.Repository;

import com.example.demo.Entity.QuestCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestCompletionRepository extends JpaRepository<QuestCompletion,Integer> {
    List<QuestCompletion> findByUser_UserId(Integer userId);
}
