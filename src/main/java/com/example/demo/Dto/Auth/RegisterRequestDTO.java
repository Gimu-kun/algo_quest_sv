package com.example.demo.Dto.Auth;

import com.example.demo.Enums.UserRoleEnum;

/**
 * Data Transfer Object (DTO) dùng để nhận dữ liệu đăng ký từ Frontend (React Native)
 * khi truyền ảnh dưới dạng chuỗi Base64 trong JSON body.
 * Lưu ý: Không sử dụng thư viện Lombok.
 */
public class RegisterRequestDTO {

    private String username;
    private String email;
    private String password;
    private String fullName;

    // Role mặc định sẽ được đặt là 'player' ở Frontend, nhưng vẫn nhận để linh hoạt
    private String role;

    // Trường để nhận chuỗi Base64 của file ảnh avatar.
    // Chuỗi này thường bắt đầu bằng "data:image/jpeg;base64,...", được gửi từ React Native.
    private String avatarBase64;

    // Constructor mặc định (cần thiết cho Jackson/Spring)
    public RegisterRequestDTO() {}

    // Constructor đầy đủ (tùy chọn)
    public RegisterRequestDTO(String username, String email, String password, String fullName, String role, String avatarBase64) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.avatarBase64 = avatarBase64;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getAvatarBase64() {
        return avatarBase64;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAvatarBase64(String avatarBase64) {
        this.avatarBase64 = avatarBase64;
    }
}
