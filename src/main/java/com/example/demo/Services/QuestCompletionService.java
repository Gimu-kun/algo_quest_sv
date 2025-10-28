package com.example.demo.Services;

import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public void deleteQuestCompletion(Integer id) {
        questCompletionRepository.deleteById(id);
    }
}
