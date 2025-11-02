package com.example.demo.Controllers;

import com.example.demo.Dto.Quest.QuestStatusDTO;
import com.example.demo.Entity.QuestCompletion;
import com.example.demo.Services.QuestCompletionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quest-completions")
@CrossOrigin("*")
public class QuestCompletionController {

    private final QuestCompletionService questCompletionService;

    public QuestCompletionController(QuestCompletionService questCompletionService) {
        this.questCompletionService = questCompletionService;
    }

    @GetMapping("/status/user/{userId}/topic/{topicId}")
    public ResponseEntity<List<QuestStatusDTO>> getQuestsStatusForUserAndTopic(
            @PathVariable Integer userId,
            @PathVariable Integer topicId) {

        List<QuestStatusDTO> statuses = questCompletionService.getQuestsStatusForUserAndTopic(userId, topicId);

        if (statuses.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(statuses);
    }

    @GetMapping
    public ResponseEntity<List<QuestCompletion>> getAllCompletions() {
        return ResponseEntity.ok(questCompletionService.getAllCompletions());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<QuestCompletion>> getCompletionsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(questCompletionService.getCompletionsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestCompletion> getCompletionById(@PathVariable Integer id) {
        return questCompletionService.getCompletionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<QuestCompletion> createCompletion(
            @RequestParam Integer userId,
            @RequestParam Integer questId,
            @RequestParam(required = false, defaultValue = "0") Integer score,
            @RequestParam(required = false, defaultValue = "0") Integer xpEarned
    ) {
        QuestCompletion completion = questCompletionService.createQuestCompletion(userId, questId, score, xpEarned);
        return ResponseEntity.ok(completion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestCompletion> updateCompletion(
            @PathVariable Integer id,
            @RequestBody QuestCompletion updated
    ) {
        return ResponseEntity.ok(questCompletionService.updateQuestCompletion(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompletion(@PathVariable Integer id) {
        questCompletionService.deleteQuestCompletion(id);
        return ResponseEntity.noContent().build();
    }
}
