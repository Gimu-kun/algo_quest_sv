package com.example.demo.Repository;

import com.example.demo.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Optional<Topic> findByTopicName(String topicName);

    @Query("SELECT t FROM Topic t LEFT JOIN FETCH t.quests")
    List<Topic> findAllWithQuests();
}
