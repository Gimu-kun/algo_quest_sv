package com.example.demo.Services;

import com.example.demo.Entity.User;
import com.example.demo.Enums.UserRoleEnum;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Utils.PasswordsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Tạo user mới
    public User createUser(User user) {
        user.setPasswordHash(PasswordsUtil.hashPassword(user.getPasswordHash()));
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(UserRoleEnum.player);
        return userRepository.save(user);
    }

    // Lấy tất cả user
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lấy user theo id
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Cập nhật user
    public Optional<User> updateUser(Integer id, User newUser) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(newUser.getFullName());
            user.setEmail(newUser.getEmail());
            user.setAvatar(newUser.getAvatar());
            // Nếu có password mới thì hash lại
            if (newUser.getPasswordHash() != null && !newUser.getPasswordHash().isEmpty()) {
                user.setPasswordHash(PasswordsUtil.hashPassword(newUser.getPasswordHash()));
            }
            return userRepository.save(user);
        });
    }

    // Xóa user
    public boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Kiểm tra login
    public Optional<User> login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> PasswordsUtil.checkPassword(password, user.getPasswordHash()));
    }
}
