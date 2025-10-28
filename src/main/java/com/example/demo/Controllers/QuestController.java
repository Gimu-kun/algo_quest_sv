package com.example.demo.Controllers;

import com.example.demo.Entity.Quest;
import com.example.demo.Services.QuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quests")
@Tag(name = "Quest API", description = "CRUD operations for Quest entity")
public class QuestController {

    @Autowired
    private QuestService questService;

    @Operation(summary = "Get all quests")
    @GetMapping
    public List<Quest> getAllQuests() {
        return questService.getAllQuests();
    }

    @Operation(summary = "Get quest by ID")
    @GetMapping("/{id}")
    public Quest getQuestById(@PathVariable Integer id) {
        return questService.getQuestById(id)
                .orElseThrow(() -> new RuntimeException("Quest not found with id " + id));
    }

    @Operation(summary = "Create a new quest")
    @PostMapping
    public Quest createQuest(@RequestBody Quest quest) {
        return questService.createQuest(quest);
    }

    @Operation(summary = "Update an existing quest")
    @PutMapping("/{id}")
    public Quest updateQuest(@PathVariable Integer id, @RequestBody Quest questDetails) {
        return questService.updateQuest(id, questDetails);
    }

    @Operation(summary = "Delete quest by ID")
    @DeleteMapping("/{id}")
    public String deleteQuest(@PathVariable Integer id) {
        questService.deleteQuest(id);
        return "Quest with ID " + id + " deleted successfully.";
    }
}
