package com.example.demo.Repository;

import com.example.demo.Entity.BattleParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BattleParticipantRepository extends JpaRepository<BattleParticipant,Integer> {
    List<BattleParticipant> findByBattle_BattleId(Integer battleId);
}
