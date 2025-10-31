package com.example.demo.Services;

import com.example.demo.Dto.UserProgress.EarnedBadgeDto;
import com.example.demo.Dto.UserProgress.ProgressSummaryDto;
import com.example.demo.Dto.UserProgress.TopicCompletionDto;
import com.example.demo.Dto.UserProgress.UserProgressDetailDto;
import com.example.demo.Entity.*;
import com.example.demo.Repository.*;
import jakarta.persistence.criteria.Join;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserProgressService {

    private final UserRepository userRepository;
    private final UserProgressRepository userProgressRepository;
    private final QuestCompletionRepository questCompletionRepository;
    private final UserBadgeRepository userBadgeRepository;
    private final TopicRepository topicRepository;

    public UserProgressService(
            UserRepository userRepository,
            UserProgressRepository userProgressRepository,
            QuestCompletionRepository questCompletionRepository,
            UserBadgeRepository userBadgeRepository,
            TopicRepository topicRepository) {

        this.userRepository = userRepository;
        this.userProgressRepository = userProgressRepository;
        this.questCompletionRepository = questCompletionRepository;
        this.userBadgeRepository = userBadgeRepository;
        this.topicRepository = topicRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserProgressDetailDto> getPaginatedUserStats(
            Pageable pageable,
            String search,
            Integer level
    ) {
        // Khởi tạo Specification với một điều kiện luôn đúng (always true).
        // Điều này thay thế cho Specification.where(null) đã bị lỗi thời.
        Specification<User> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        // 1. Xử lý Tìm kiếm (search)
        if (search != null && !search.trim().isEmpty()) {
            String likeSearch = "%" + search.trim().toLowerCase() + "%";

            Specification<User> searchSpec = (root, query, cb) -> cb.or(
                    // Tìm kiếm theo username
                    cb.like(cb.lower(root.get("username")), likeSearch),
                    // Tìm kiếm theo fullName
                    cb.like(cb.lower(root.get("fullName")), likeSearch)
            );
            // Nối điều kiện tìm kiếm vào chuỗi
            spec = spec.and(searchSpec);
        }

        // 2. Xử lý Lọc theo Cấp độ (level)
        if (level != null) {
            Specification<User> levelSpec = (root, query, cb) -> {
                // **Quan trọng:** Thay "userProgress" bằng tên trường @OneToOne
                // trong entity User (trỏ đến entity UserProgress).
                Join<User, UserProgress> progressJoin = root.join("userProgress");
                return cb.equal(progressJoin.get("currentLevel"), level);
            };
            // Nối điều kiện lọc cấp độ vào chuỗi
            spec = spec.and(levelSpec);
        }

        // 3. Truy vấn Repository bằng Specification đã xây dựng
        Page<User> userPage = userRepository.findAll(spec, pageable);

        // 4. Ánh xạ kết quả (Map Page<User> sang Page<UserProgressStatsDTO>)
        List<UserProgressDetailDto> dtoList = userPage.getContent().stream()
                .map(this::mapUserToProgressStatsDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(dtoList, pageable, userPage.getTotalElements());
    }

    @Transactional(readOnly = true) // Đảm bảo việc đọc dữ liệu được tối ưu
    public UserProgressDetailDto mapUserToProgressStatsDTO(User user) {

        // -----------------------------------------------------------
        // 1. Lấy và tạo Progress Summary DTO
        // -----------------------------------------------------------

        // Lấy UserProgress (giả định 1 User có 1 UserProgress)
        UserProgress userProgress = userProgressRepository.findByUser(user)
                .orElseGet(UserProgress::new); // Tạo một đối tượng rỗng nếu không tìm thấy

        // Đếm số Quest đã hoàn thành (chỉ tính Quest duy nhất)
        Integer questsCompletedCount = Math.toIntExact(questCompletionRepository.countDistinctQuestByUser(user));

        // Đếm số Huy hiệu đã đạt được
        Integer badgesEarnedCount = userBadgeRepository.countByUser(user);

        ProgressSummaryDto summaryDto = new ProgressSummaryDto(
                userProgress.getTotalXp(),
                userProgress.getCurrentLevel(),
                questsCompletedCount,
                badgesEarnedCount
        );

        // -----------------------------------------------------------
        // 2. Lấy và tạo Topic Completion Rates DTO
        // -----------------------------------------------------------
        List<TopicCompletionDto> topicRatesList = calculateTopicCompletion(user);

        // -----------------------------------------------------------
        // 3. Lấy và tạo Earned Badges DTO
        // -----------------------------------------------------------
        List<EarnedBadgeDto> badgesList = fetchEarnedBadges(user);

        // -----------------------------------------------------------
        // 4. Xây dựng và trả về DTO cuối cùng
        // -----------------------------------------------------------
        return new UserProgressDetailDto(
                user.getUserId(),
                user.getUsername(),
                user.getFullName(),
                summaryDto,
                topicRatesList,
                badgesList
        );
    }

    private List<TopicCompletionDto> calculateTopicCompletion(User user) {
        // Lấy tất cả Topics và Quest của chúng
        // Giả định Topic Repository có phương thức này để JOIN FETCH Quests
        List<Topic> allTopics = topicRepository.findAllWithQuests();

        // Lấy tất cả Quest IDs mà User đã hoàn thành (chỉ lấy unique quest)
        Set<Integer> completedQuestIds = questCompletionRepository.findDistinctQuestIdsByUser(user);

        List<TopicCompletionDto> topicRates = new ArrayList<>();

        for (Topic topic : allTopics) {
            int totalQuests = topic.getQuests().size(); // Lấy tổng số Quest trong Topic
            int completedQuests = 0;

            if (totalQuests > 0) {
                // Đếm số Quest đã hoàn thành trong Topic này
                completedQuests = (int) topic.getQuests().stream()
                        .filter(quest -> completedQuestIds.contains(quest.getQuestId()))
                        .count();
            }

            double percentage = (totalQuests > 0) ?
                    (double) completedQuests / totalQuests * 100.0 : 0.0;

            TopicCompletionDto dto = new TopicCompletionDto(
                    topic.getTopicId(),
                    topic.getTopicName(),
                    totalQuests,
                    completedQuests,
                    percentage
            );
            topicRates.add(dto);
        }

        return topicRates;
    }

    private List<EarnedBadgeDto> fetchEarnedBadges(User user) {
        // Giả định UserBadgeRepository có phương thức JOIN FETCH Badge
        List<UserBadge> userBadges = userBadgeRepository.findByUserWithBadgeDetails(user);

        return userBadges.stream()
                .map(userBadge -> {
                    Badge badge = userBadge.getBadge();
                    return new EarnedBadgeDto(
                            badge.getBadgeId(),
                            badge.getBadgeName(),
                            badge.getDescription(),
                            badge.getImageUrl(),
                            userBadge.getAwardedAt()
                    );
                })
                .collect(Collectors.toList());
    }
}