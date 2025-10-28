package com.example.demo.Repository;

import com.example.demo.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Optional<Topic> findByTopicName(String topicName);
}
