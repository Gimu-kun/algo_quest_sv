package com.example.demo.Repository;

import com.example.demo.Entity.QuestCompletion;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface QuestCompletionRepository extends JpaRepository<QuestCompletion,Integer> {
    List<QuestCompletion> findByUser_UserId(Integer userId);

    // 1. Phương thức ĐẾM SỐ QUEST ĐÃ HOÀN THÀNH (Cần trả về Long hoặc Integer)
    // *** SỬA LỖI NÀY ***
    @Query("SELECT COUNT(DISTINCT qc.quest.questId) FROM QuestCompletion qc WHERE qc.user = :user")
    Long countDistinctQuestByUser(User user);
    // Khuyến nghị sử dụng Long cho các phép đếm

    // 2. Phương thức lấy ra các ID Quest duy nhất (Trả về Set<Integer>)
    @Query("SELECT DISTINCT qc.quest.questId FROM QuestCompletion qc WHERE qc.user = :user")
    Set<Integer> findDistinctQuestIdsByUser(User user);
}
