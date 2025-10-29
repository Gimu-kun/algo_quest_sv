package com.example.demo.Dto.Auth;

import com.example.demo.Entity.User;
import com.example.demo.Enums.UserRoleEnum;

public class AuthResponse {
    private boolean isAuthenticated;
    private UserRoleEnum role;
    private Integer userId;
    private String username;

    // Constructors
    public AuthResponse(boolean isAuthenticated, UserRoleEnum role, Integer userId, String username) {
        this.isAuthenticated = isAuthenticated;
        this.role = role;
        this.userId = userId;
        this.username = username;
    }

    // Getters and Setters (Tạo tự động)
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}