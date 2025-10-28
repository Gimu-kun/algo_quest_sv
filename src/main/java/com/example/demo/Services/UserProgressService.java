package com.example.demo.Services;

import com.example.demo.Entity.User;
import com.example.demo.Entity.UserProgress;
import com.example.demo.Repository.UserProgressRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProgressService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private UserRepository userRepository;

    // ✅ Tạo mới progress cho user (nếu chưa có)
    public Optional<UserProgress> createProgressForUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) return Optional.empty();

        // kiểm tra user đã có progress chưa
        if (userProgressRepository.findByUser_UserId(userId).isPresent()) {
            return Optional.empty(); // đã có rồi
        }

        UserProgress progress = new UserProgress();
        progress.setUser(userOpt.get());
        progress.setTotalXp(0);
        progress.setCurrentLevel(1);
        return Optional.of(userProgressRepository.save(progress));
    }

    // ✅ Lấy toàn bộ progress
    public List<UserProgress> getAllProgress() {
        return userProgressRepository.findAll();
    }

    // ✅ Lấy progress theo ID
    public Optional<UserProgress> getProgressById(Integer id) {
        return userProgressRepository.findById(id);
    }

    // ✅ Lấy progress theo user ID
    public Optional<UserProgress> getProgressByUserId(Integer userId) {
        return userProgressRepository.findByUser_UserId(userId);
    }

    // ✅ Cập nhật progress
    public Optional<UserProgress> updateProgress(Integer id, UserProgress newData) {
        return userProgressRepository.findById(id).map(progress -> {
            if (newData.getTotalXp() != null)
                progress.setTotalXp(newData.getTotalXp());
            if (newData.getCurrentLevel() != null)
                progress.setCurrentLevel(newData.getCurrentLevel());
            return userProgressRepository.save(progress);
        });
    }

    // ✅ Xóa progress
    public boolean deleteProgress(Integer id) {
        if (userProgressRepository.existsById(id)) {
            userProgressRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
