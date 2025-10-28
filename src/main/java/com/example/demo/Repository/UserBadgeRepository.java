package com.example.demo.Repository;

import com.example.demo.Entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBadgeRepository extends JpaRepository<UserBadge,Integer> {
}
