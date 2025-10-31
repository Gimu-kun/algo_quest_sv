package com.example.demo.Repository;

import com.example.demo.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.awt.print.Pageable;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
