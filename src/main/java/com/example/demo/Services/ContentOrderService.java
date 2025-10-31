package com.example.demo.Services;

import com.example.demo.Dto.OrderUpdateRequest;
import com.example.demo.Entity.Quest;
import com.example.demo.Entity.Question;
import com.example.demo.Repository.QuestRepository;
import com.example.demo.Repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContentOrderService {

    @Autowired
    private QuestRepository questRepository; // Giả định Repository đã tồn tại

    @Autowired
    private QuestionRepository questionRepository; // Giả định Repository đã tồn tại

    // ===============================================
    // DỊCH VỤ ĐIỀU CHỈNH THỨ TỰ QUEST
    // ===============================================
    @Transactional
    public List<Quest> updateQuestOrder(OrderUpdateRequest request) {
        // 1. Lấy tất cả ID cần cập nhật từ request
        List<Integer> ids = request.getItems().stream()
                .map(OrderUpdateRequest.OrderItem::getId)
                .collect(Collectors.toList());

        // 2. Tải tất cả Quest cần cập nhật từ Database
        List<Quest> questsToUpdate = questRepository.findAllById(ids);

        // 3. Tạo Map (ID -> orderIndex mới) để tra cứu nhanh
        Map<Integer, Integer> newOrderMap = request.getItems().stream()
                .collect(Collectors.toMap(
                        OrderUpdateRequest.OrderItem::getId,
                        OrderUpdateRequest.OrderItem::getOrderIndex
                ));

        // 4. Cập nhật orderIndex mới cho từng Quest
        questsToUpdate.forEach(quest -> {
            Integer newOrder = newOrderMap.get(quest.getQuestId());
            if (newOrder != null) {
                quest.setOrderIndex(newOrder);
            }
        });

        // 5. Lưu lại tất cả
        return questRepository.saveAll(questsToUpdate);
    }

    // ===============================================
    // DỊCH VỤ ĐIỀU CHỈNH THỨ TỰ QUESTION
    // ===============================================
    @Transactional
    public List<Question> updateQuestionOrder(OrderUpdateRequest request) {
        // 1. Lấy tất cả ID cần cập nhật từ request
        List<Integer> ids = request.getItems().stream()
                .map(OrderUpdateRequest.OrderItem::getId)
                .collect(Collectors.toList());

        // 2. Tải tất cả Question cần cập nhật từ Database
        List<Question> questionsToUpdate = questionRepository.findAllById(ids);

        // 3. Tạo Map (ID -> orderIndex mới) để tra cứu nhanh
        Map<Integer, Integer> newOrderMap = request.getItems().stream()
                .collect(Collectors.toMap(
                        OrderUpdateRequest.OrderItem::getId,
                        OrderUpdateRequest.OrderItem::getOrderIndex
                ));

        // 4. Cập nhật orderIndex mới cho từng Question
        questionsToUpdate.forEach(question -> {
            Integer newOrder = newOrderMap.get(question.getQuestionId());
            if (newOrder != null) {
                question.setOrderIndex(newOrder);
            }
        });

        // 5. Lưu lại tất cả
        return questionRepository.saveAll(questionsToUpdate);
    }
}