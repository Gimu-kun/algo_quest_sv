package com.example.demo.Services;

import com.example.demo.Dto.Badge.IBadgeUpdateDto;
import com.example.demo.Entity.Badge;
import com.example.demo.Repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BadgeService {

    private final BadgeRepository badgeRepository;

    @Autowired
    public BadgeService(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    // Tạo mới
    public Badge createBadge(Badge badge) {
        if (badgeRepository.findByBadgeName(badge.getBadgeName()).isPresent()) {
            throw new IllegalStateException("Badge name already exists: " + badge.getBadgeName());
        }
        return badgeRepository.save(badge);
    }

    // Lấy tất cả huy hiệu
    public List<Badge> getAllBadges() {
        return badgeRepository.findAll();
    }

    // Lấy huy hiệu bằng id
    public Optional<Badge> getBadgeById(Integer id) {
        return badgeRepository.findById(id);
    }

    // Cập nhật huy hiệu
    public Badge updateBadge(Integer id, IBadgeUpdateDto updateDto) {
        return badgeRepository.findById(id)
                .map(existingBadge -> {
                    updateDto.getBadgeName().ifPresent(existingBadge::setBadgeName);
                    updateDto.getDescription().ifPresent(existingBadge::setDescription);
                    updateDto.getImageUrl().ifPresent(existingBadge::setImageUrl);
                    return badgeRepository.save(existingBadge);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Badge not found with id " + id));
    }

    // Xoá huy hiệu
    public void deleteBadge(Integer id) {
        if (!badgeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Badge not found with id " + id);
        }
        badgeRepository.deleteById(id);
    }

    // Class Exception xử lý lỗi 404
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
