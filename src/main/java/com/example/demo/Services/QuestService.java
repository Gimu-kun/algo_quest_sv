package com.example.demo.Services;

import com.example.demo.Entity.Quest;
import com.example.demo.Repository.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public List<Quest> getAllQuests() {
        return questRepository.findAll();
    }

    public Optional<Quest> getQuestById(Integer id) {
        return questRepository.findById(id);
    }

    public Quest createQuest(Quest quest) {
        return questRepository.save(quest);
    }

    public Quest updateQuest(Integer id, Quest questDetails) {
        Quest quest = questRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quest not found with id " + id));

        quest.setQuestName(questDetails.getQuestName());
        quest.setQuestType(questDetails.getQuestType());
        quest.setDifficulty(questDetails.getDifficulty());
        quest.setRequiredXp(questDetails.getRequiredXp());
        quest.setTopic(questDetails.getTopic());
        quest.setQuestions(questDetails.getQuestions());

        return questRepository.save(quest);
    }

    public void deleteQuest(Integer id) {
        if (!questRepository.existsById(id)) {
            throw new RuntimeException("Quest not found with id " + id);
        }
        questRepository.deleteById(id);
    }
}
