package com.example.demo.Services;

import com.example.demo.Entity.Topic;
import com.example.demo.Repository.TopicRepository;
import com.example.demo.Dto.Topic.TopicUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    @Autowired
    public TopicService(TopicRepository topicRepository) {
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
}
