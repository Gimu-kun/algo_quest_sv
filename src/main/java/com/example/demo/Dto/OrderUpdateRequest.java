package com.example.demo.Dto;

import java.util.List;

public class OrderUpdateRequest {
    public static class OrderItem {
        private Integer id;
        private Integer orderIndex;

        // Constructor mặc định
        public OrderItem() {}

        // Getters and Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getOrderIndex() {
            return orderIndex;
        }

        public void setOrderIndex(Integer orderIndex) {
            this.orderIndex = orderIndex;
        }
    }

    private List<OrderItem> items;

    // Constructor mặc định
    public OrderUpdateRequest() {}

    // Getters and Setters
    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}