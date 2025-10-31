package com.example.demo.Repository;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserBadge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBadgeRepository extends JpaRepository<UserBadge,Integer> {
    Integer countByUser(User user);

    @Query("SELECT ub FROM UserBadge ub JOIN FETCH ub.badge WHERE ub.user = :user")
    List<UserBadge> findByUserWithBadgeDetails(User user);
}
