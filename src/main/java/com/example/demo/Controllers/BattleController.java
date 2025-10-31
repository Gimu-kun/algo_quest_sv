package com.example.demo.Controllers;

import com.example.demo.Entity.Battle;
import com.example.demo.Entity.BattleParticipant;
import com.example.demo.Services.BattleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/battles")
@Tag(name = "Battle API", description = "CRUD cho Battle và BattleParticipant")
@CrossOrigin("*")
public class BattleController {

    @Autowired
    private BattleService battleService;

    // ======================
    // CRUD cho Battle
    // ======================

    @Operation(summary = "Lấy tất cả trận đấu (có phân trang, tìm kiếm và lọc)")
    @GetMapping
    public Page<Battle> getAllBattles(
            // THAY ĐỔI LỚN: Sử dụng Pageable để xử lý page, size, và sort tự động
            Pageable pageable,
            // Giữ lại các tham số tìm kiếm và lọc tùy chỉnh
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String status
    ) {
        // Cập nhật hàm service để nhận Pageable
        return battleService.getAllBattles(pageable, search, status);
    }

    @Operation(summary = "Lấy thông tin một trận đấu theo ID")
    @GetMapping("/{id}")
    public Battle getBattleById(@PathVariable Integer id) {
        return battleService.getBattleById(id);
    }

    @Operation(summary = "Tạo mới một trận đấu (có thể kèm danh sách người chơi)")
    @PostMapping
    public Battle createBattle(@RequestBody Battle battle) {
        return battleService.createBattle(battle);
    }

    @Operation(summary = "Cập nhật thông tin trận đấu")
    @PutMapping("/{id}")
    public Battle updateBattle(@PathVariable Integer id, @RequestBody Battle battleDetails) {
        return battleService.updateBattle(id, battleDetails);
    }

    @Operation(summary = "Xóa một trận đấu")
    @DeleteMapping("/{id}")
    public String deleteBattle(@PathVariable Integer id) {
        battleService.deleteBattle(id);
        return "Battle with ID " + id + " deleted successfully.";
    }

    // ======================
    // CRUD cho Participant
    // ======================

    @Operation(summary = "Lấy danh sách người tham gia của một trận đấu")
    @GetMapping("/{battleId}/participants")
    public List<BattleParticipant> getParticipants(@PathVariable Integer battleId) {
        return battleService.getParticipantsByBattleId(battleId);
    }

    @Operation(summary = "Thêm người chơi vào một trận đấu")
    @PostMapping("/{battleId}/participants")
    public BattleParticipant addParticipant(@PathVariable Integer battleId, @RequestBody BattleParticipant participant) {
        return battleService.addParticipantToBattle(battleId, participant);
    }

    @Operation(summary = "Cập nhật thông tin người chơi trong trận đấu")
    @PutMapping("/participants/{participantId}")
    public BattleParticipant updateParticipant(@PathVariable Integer participantId, @RequestBody BattleParticipant participant) {
        return battleService.updateParticipant(participantId, participant);
    }

    @Operation(summary = "Xóa người chơi khỏi trận đấu")
    @DeleteMapping("/participants/{participantId}")
    public String deleteParticipant(@PathVariable Integer participantId) {
        battleService.deleteParticipant(participantId);
        return "Participant with ID " + participantId + " deleted successfully.";
    }
}
