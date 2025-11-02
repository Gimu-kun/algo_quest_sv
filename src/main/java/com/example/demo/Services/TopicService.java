package com.example.demo.Services;

import com.example.demo.Dto.Quest.QuestSummaryDto;
import com.example.demo.Dto.Topic.TopicSummaryDto;
import com.example.demo.Entity.Quest;
import com.example.demo.Entity.Topic;
import com.example.demo.Repository.QuestRepository;
import com.example.demo.Repository.TopicRepository;
import com.example.demo.Dto.Topic.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final QuestRepository questRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository, QuestRepository questRepository) {
        this.questRepository = questRepository;
        this.topicRepository = topicRepository;
    }

    // Tạo chủ đề mới
    public Topic createTopic(Topic topic) {
        if (topicRepository.findByTopicName(topic.getTopicName()).isPresent()) {
            throw new IllegalStateException("Topic name already exists: " + topic.getTopicName());
        }
        return topicRepository.save(topic);
    }

    // Lấy tất cả chủ đề
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    public List<TopicSummaryDto> getAllTopicSummaries() {
        return topicRepository.findAll().stream()
                .map(this::convertToSummaryDto)
                .collect(java.util.stream.Collectors.toList());
    }

    private TopicSummaryDto convertToSummaryDto(Topic topic) {
        int questCount = topic.getQuests() != null ? topic.getQuests().size() : 0;
        return new TopicSummaryDto(
                topic.getTopicId(),
                topic.getTopicName(),
                topic.getDescription(),
                topic.getOrderIndex(),
                questCount
        );
    }

    // Lấy chủ đề theo Id
    public Optional<Topic> getTopicById(Integer id) {
        return topicRepository.findById(id);
    }

    // Cập nhật chủ đề
    public Topic updateTopic(Integer id, TopicUpdateDto updateDto) {
        return topicRepository.findById(id)
                .map(existingTopic -> {

                    updateDto.getTopicName().ifPresent(newName -> {
                        existingTopic.setTopicName(newName);
                    });

                    updateDto.getDescription().ifPresent(newDescription -> {
                        existingTopic.setDescription(newDescription);
                    });

                    updateDto.getOrderIndex().ifPresent(newOrderIndex -> {
                        existingTopic.setOrderIndex(newOrderIndex);
                    });

                    return topicRepository.save(existingTopic);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found with id " + id));
    }

    // Xoá chủ đề
    public void deleteTopic(Integer id) {
        if (!topicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Topic not found with id " + id);
        }

        topicRepository.deleteById(id);
    }

    // Exception tuỳ chỉnh
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public List<QuestSummaryDto> getQuestSummariesByTopicId(Integer topicId) {

        // 1. Kiểm tra xem Topic có tồn tại không (Tùy chọn, nên có)
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("Topic not found with id " + topicId);
        }

        // 2. Lấy danh sách Quests
        // Chúng ta sử dụng QuestRepository để tìm trực tiếp các quests theo Topic ID,
        // và sắp xếp theo OrderIndex
        List<Quest> quests = questRepository.findByTopic_TopicIdOrderByOrderIndexAsc(topicId);

        // 3. Ánh xạ từ Quest Entity sang QuestSummaryDto
        return quests.stream()
                .map(this::convertToQuestSummaryDto)
                .collect(java.util.stream.Collectors.toList());
    }

    private QuestSummaryDto convertToQuestSummaryDto(Quest quest) {
        return new QuestSummaryDto(
                quest.getQuestId(),
                quest.getQuestName(),
                quest.getOrderIndex(),
                quest.getQuestType().name(),
                quest.getDifficulty().name(),
                quest.getRequiredXp()
        );
    }
}
