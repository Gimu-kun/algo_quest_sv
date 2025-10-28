package com.example.demo.Repository;

import com.example.demo.Entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BadgeRepository extends JpaRepository<Badge,Integer> {
    Optional<Badge> findByBadgeName(String badgeName);
}
