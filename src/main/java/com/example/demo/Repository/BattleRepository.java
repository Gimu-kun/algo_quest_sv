package com.example.demo.Repository;

import com.example.demo.Entity.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BattleRepository extends JpaRepository<Battle,Integer>, JpaSpecificationExecutor<Battle> {
    @Query("SELECT b FROM Battle b " +
            "LEFT JOIN FETCH b.topic " +
            "LEFT JOIN FETCH b.participants bp " +
            "LEFT JOIN FETCH bp.user")
    List<Battle> findAllWithDetails();

    @EntityGraph(attributePaths = {"topic", "participants.user"})
    Page<Battle> findAll(org.springframework.data.jpa.domain.Specification<Battle> spec, Pageable pageable);
}
