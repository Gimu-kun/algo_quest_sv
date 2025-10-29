package com.example.demo.Controllers;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demo.Dto.Auth.AuthResponse;
import com.example.demo.Entity.User;
import com.example.demo.Enums.UserRoleEnum;
import com.example.demo.Services.UserService;
import com.example.demo.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Tạo mới user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.ok(created);
    }

    // Lấy tất cả user
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Lấy user theo id
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Cập nhật user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody User user) {
        return userService.updateUser(id, user)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Xóa user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? ResponseEntity.ok("Deleted") : ResponseEntity.notFound().build();
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        return userService.login(username, password)
                .map(user -> {
                    String token = jwtUtil.createToken(String.valueOf(user.getUserId()), user.getRole());
                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("user", user);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(401).build());
    }

    // Xác thực
    @GetMapping("/auth")
    public ResponseEntity<AuthResponse> authenticateAdmin(@RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {

        AuthResponse invalidResponse = new AuthResponse(false, null, null, null);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok(invalidResponse);
        }

        String token = authHeader.substring(7);

        DecodedJWT decodedJWT = jwtUtil.decodeToken(token);

        if (decodedJWT == null) {
            return ResponseEntity.ok(invalidResponse);
        }

        try {
            String userIdStr = decodedJWT.getSubject();
            String roleStr = decodedJWT.getClaim("role").asString();
            UserRoleEnum role = UserRoleEnum.valueOf(roleStr);
            Integer userId = Integer.parseInt(userIdStr);

            Optional<User> userOpt = userService.getUserById(userId);
            String username = userOpt.map(User::getUsername).orElse(null);

            boolean isAuthenticated = (role == UserRoleEnum.admin);

            AuthResponse response = new AuthResponse(isAuthenticated, role, userId, username);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.ok(invalidResponse);
        }
    }
}
