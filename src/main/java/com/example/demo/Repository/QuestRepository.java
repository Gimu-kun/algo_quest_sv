package com.example.demo.Repository;

import com.example.demo.Entity.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest,Integer> {
}
