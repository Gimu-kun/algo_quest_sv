package com.example.demo.Controllers;

import com.example.demo.Dto.UserProgress.UserProgressDetailDto;
import com.example.demo.Services.UserProgressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-progress")
@CrossOrigin("*")
public class UserProgressController {

    private final UserProgressService userProgressService;

    public UserProgressController(UserProgressService userProgressService) {
        this.userProgressService = userProgressService;
    }

    @GetMapping("/stats")
    public ResponseEntity<Page<UserProgressDetailDto>> getPaginatedUserStats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(required = false) String search,
            // SỬA LỖI Ở ĐÂY: Dùng Integer (kiểu đối tượng) và cung cấp giá trị mặc định là null
            // Khi không có tham số được gửi (hoặc gửi rỗng), nó sẽ là null.
            // Tuy nhiên, nếu frontend gửi chuỗi "undefined", bạn cần xử lý ở frontend.
            @RequestParam(required = false) Integer level) {

        // Nếu bạn muốn xử lý giá trị "undefined" từ frontend một cách mạnh mẽ hơn:
        if (level == null && search != null && "undefined".equalsIgnoreCase(search)) {
            search = null;
        }
        // Nếu giá trị level là "undefined" (chuỗi), nó đã bị lỗi type mismatch trước khi vào hàm.

        // Giải pháp tối ưu: Chỉ dựa vào `@RequestParam(required = false)` và
        // đảm bảo frontend gửi giá trị là null/undefined/rỗng khi không chọn.

        Pageable pageable = PageRequest.of(page, size);

        Page<UserProgressDetailDto> statsPage = userProgressService.getPaginatedUserStats(pageable, search, level);

        return ResponseEntity.ok(statsPage);
    }
}