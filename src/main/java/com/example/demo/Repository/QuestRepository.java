package com.example.demo.Repository;

import com.example.demo.Entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest,Integer> {
    List<Quest> findByTopic_TopicIdOrderByOrderIndexAsc(Integer topicId);
}
