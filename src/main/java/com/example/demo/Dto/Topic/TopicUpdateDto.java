package com.example.demo.Dto.Topic;

import java.util.Optional;

public class TopicUpdateDto {

    private Optional<String> topicName = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<Integer> orderIndex = Optional.empty();

    public TopicUpdateDto() {
    }

    public TopicUpdateDto(Optional<String> topicName, Optional<String> description, Optional<Integer> orderIndex) {
        this.topicName = topicName;
        this.description = description;
        this.orderIndex = orderIndex;
    }

    public Optional<String> getTopicName() {
        return topicName;
    }

    public void setTopicName(Optional<String> topicName) {
        this.topicName = topicName;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public void setDescription(Optional<String> description) {
        this.description = description;
    }

    public Optional<Integer> getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Optional<Integer> orderIndex) {
        this.orderIndex = orderIndex;
    }
}
