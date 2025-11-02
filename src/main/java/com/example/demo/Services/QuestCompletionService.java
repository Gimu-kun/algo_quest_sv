package com.example.demo.Services;

import com.example.demo.Dto.Quest.QuestStatusDTO;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestCompletionService {

    private final QuestCompletionRepository questCompletionRepository;
    private final UserRepository userRepository;
    private final QuestRepository questRepository;
    private final UserProgressRepository userProgressRepository;

    public QuestCompletionService(
            QuestCompletionRepository questCompletionRepository,
            UserRepository userRepository,
            QuestRepository questRepository,
            UserProgressRepository userProgressRepository
    ) {
        this.questCompletionRepository = questCompletionRepository;
        this.userRepository = userRepository;
        this.questRepository = questRepository;
        this.userProgressRepository = userProgressRepository;
    }

    public List<QuestCompletion> getAllCompletions() {
        return questCompletionRepository.findAll();
    }

    public List<QuestCompletion> getCompletionsByUser(Integer userId) {
        return questCompletionRepository.findByUser_UserId(userId);
    }

    public Optional<QuestCompletion> getCompletionById(Integer id) {
        return questCompletionRepository.findById(id);
    }

    public List<QuestCompletion> getCompletionsByUserAndTopic(Integer userId, Integer topicId) {
        return questCompletionRepository.findByUser_UserIdAndQuest_Topic_TopicId(userId, topicId);
    }

    @Transactional
    public QuestCompletion createQuestCompletion(Integer userId, Integer questId, Integer score, Integer xpEarned) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Quest quest = questRepository.findById(questId)
                .orElseThrow(() -> new RuntimeException("Quest not found"));

        QuestCompletion completion = new QuestCompletion();
        completion.setUser(user);
        completion.setQuest(quest);
        completion.setScore(score);
        completion.setXpEarned(xpEarned);
        completion.setCompletionTime(LocalDateTime.now());

        QuestCompletion saved = questCompletionRepository.save(completion);

        // Cập nhật tiến độ người dùng
        Optional<UserProgress> progress = userProgressRepository.findByUser_UserId(userId);
        UserProgress userProgress = userProgressRepository.findByUser_UserId(userId)
                .orElseGet(() -> {
                    UserProgress newProgress = new UserProgress();
                    newProgress.setUser(user);
                    newProgress.setTotalXp(0);
                    newProgress.setCurrentLevel(1);
                    return newProgress;
                });

        userProgress.setTotalXp(userProgress.getTotalXp() + xpEarned);
        userProgress.setCurrentLevel(1 + userProgress.getTotalXp() / 100);
        userProgress.setLastUpdated(LocalDateTime.now());
        userProgressRepository.save(userProgress);

        return saved;
    }

    public QuestCompletion updateQuestCompletion(Integer id, QuestCompletion updatedCompletion) {
        return questCompletionRepository.findById(id)
                .map(existing -> {
                    existing.setScore(updatedCompletion.getScore());
                    existing.setXpEarned(updatedCompletion.getXpEarned());
                    existing.setCompletionTime(LocalDateTime.now());
                    return questCompletionRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("QuestCompletion not found"));
    }

    public List<QuestStatusDTO> getQuestsStatusForUserAndTopic(Integer userId, Integer topicId) {
        // 1. Lấy tất cả Quests của Topic, sắp xếp theo orderIndex
        List<Quest> quests = questRepository.findByTopic_TopicIdOrderByOrderIndexAsc(topicId);

        // 2. Lấy danh sách các ID Quest đã hoàn thành của người dùng
        Set<Integer> completedQuestIds = questCompletionRepository.findDistinctQuestIdsByUser(
                userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"))
        );

        List<QuestStatusDTO> questStatuses = new ArrayList<>();
        boolean isPreviousQuestCompleted = true; // Bắt đầu bằng true để Quest đầu tiên luôn mở

        for (Quest quest : quests) {
            boolean isCompleted = completedQuestIds.contains(quest.getQuestId());
            boolean isLocked = !isPreviousQuestCompleted;

            // Tạo DTO
            QuestStatusDTO dto = new QuestStatusDTO(
                    quest.getQuestId(),
                    quest.getQuestName(),
                    quest.getOrderIndex(),
                    isCompleted,
                    isLocked,
                    quest.getDifficulty(),
                    quest.getQuestType(),
                    quest.getRequiredXp()
            );
            questStatuses.add(dto);

            // Cập nhật trạng thái cho Quest tiếp theo
            if (!isCompleted) {
                // Nếu Quest hiện tại chưa hoàn thành, tất cả các Quest sau nó sẽ bị khóa
                isPreviousQuestCompleted = false;
            }
        }

        return questStatuses;
    }

    public void deleteQuestCompletion(Integer id) {
        questCompletionRepository.deleteById(id);
    }
}
