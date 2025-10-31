package com.example.demo.Services;

import com.example.demo.Entity.Battle;
import com.example.demo.Entity.BattleParticipant;
import com.example.demo.Entity.User;
import com.example.demo.Entity.UserRating;
import com.example.demo.Repository.BattleParticipantRepository;
import com.example.demo.Repository.BattleRepository;
import com.example.demo.Repository.UserRatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BattleService {

    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private BattleParticipantRepository participantRepository;

    @Autowired
    private UserRatingRepository userRatingRepository;

    public Page<Battle> getAllBattles(Pageable pageable, String search, String status) {

        // Khởi tạo Specification trống (non-deprecated)
        Specification<Battle> spec = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        // Lọc theo Status (Trạng thái)
        if (status != null && !status.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), status));
        }

        // Tìm kiếm theo Topic Name
        if (search != null && !search.isEmpty()) {
            spec = spec.and((root, query, cb) -> {
                // Tối ưu hóa JOIN Topic nếu cần thiết
                return cb.like(
                        cb.lower(root.get("topic").get("topicName")),
                        "%" + search.toLowerCase() + "%"
                );
            });
        }

        // Thực hiện truy vấn với Specification và Pageable
        // Pageable tự động bao gồm Sort (từ tham số URL)
        return battleRepository.findAll(spec, pageable);
    }

    public Battle getBattleById(Integer id) {
        return battleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Battle not found with id " + id));
    }

    public Battle createBattle(Battle battle) {
        if (battle.getParticipants() != null) {
            battle.getParticipants().forEach(p -> p.setBattle(battle));
        }
        return battleRepository.save(battle);
    }

    public Battle updateBattle(Integer id, Battle battleDetails) {
        Battle battle = battleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Battle not found with id " + id));

        battle.setTopic(battleDetails.getTopic());
        battle.setStartTime(battleDetails.getStartTime());
        battle.setEndTime(battleDetails.getEndTime());
        battle.setStatus(battleDetails.getStatus());
        battle.setQuestionSetId(battleDetails.getQuestionSetId());

        // Cập nhật danh sách participant nếu có
        if (battleDetails.getParticipants() != null) {
            battleDetails.getParticipants().forEach(p -> p.setBattle(battle));
            battle.setParticipants(battleDetails.getParticipants());
        }

        return battleRepository.save(battle);
    }

    public void deleteBattle(Integer id) {
        if (!battleRepository.existsById(id)) {
            throw new RuntimeException("Battle not found with id " + id);
        }
        battleRepository.deleteById(id);
    }

    // ======================
    // CRUD cho Participant
    // ======================
    public List<BattleParticipant> getParticipantsByBattleId(Integer battleId) {
        return participantRepository.findByBattle_BattleId(battleId);
    }

    public BattleParticipant addParticipantToBattle(Integer battleId, BattleParticipant participant) {
        Battle battle = getBattleById(battleId);
        participant.setBattle(battle);
        return participantRepository.save(participant);
    }

    public BattleParticipant updateParticipant(Integer participantId, BattleParticipant updated) {
        BattleParticipant participant = participantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found with id " + participantId));

        participant.setScore(updated.getScore());
        participant.setRankInBattle(updated.getRankInBattle());
        participant.setWinner(updated.getWinner());
        participant.setTimeTakenMs(updated.getTimeTakenMs());
        participant.setUser(updated.getUser());

        return participantRepository.save(participant);
    }

    public void deleteParticipant(Integer participantId) {
        participantRepository.deleteById(participantId);
    }

    private int calculateElo(int playerElo, int opponentElo, boolean isWinner) {
        double k = 32;
        double expectedScore = 1.0 / (1 + Math.pow(10, (opponentElo - playerElo) / 400.0));
        double actualScore = isWinner ? 1.0 : 0.0;
        return (int) Math.round(playerElo + k * (actualScore - expectedScore));
    }

    @Transactional
    public void updateUserRatingsAfterBattle(Battle battle) {
        for (BattleParticipant participant : battle.getParticipants()) {
            User user = participant.getUser();

            UserRating rating = userRatingRepository.findByUser_UserId(user.getUserId())
                    .orElseGet(() -> {
                        UserRating newRating = new UserRating();
                        newRating.setUser(user);
                        newRating.setEloRating(1000);
                        newRating.setWinCount(0);
                        newRating.setLossCount(0);
                        return newRating;
                    });

            // Tính điểm ELO mới (đơn giản, bạn có thể thay bằng công thức riêng)
            int opponentElo = 1000; // mặc định hoặc trung bình từ đối thủ
            int newElo = calculateElo(rating.getEloRating(), opponentElo, participant.getWinner());
            rating.setEloRating(newElo);

            // Cập nhật thắng/thua
            if (participant.getWinner()) {
                rating.setWinCount(rating.getWinCount() + 1);
            } else {
                rating.setLossCount(rating.getLossCount() + 1);
            }

            rating.setLastPlayed(LocalDateTime.now());
            userRatingRepository.save(rating);
        }
    }
}
