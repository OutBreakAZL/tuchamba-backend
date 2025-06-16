package com.tuchamba.tuchamba_backend.interfaces.rest;

import com.tuchamba.tuchamba_backend.application.dto.UserRequest;
import com.tuchamba.tuchamba_backend.application.dto.UserResponse;
import com.tuchamba.tuchamba_backend.domain.model.User;
import com.tuchamba.tuchamba_backend.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest request) {
        try {
            User user = new User(request.getName(), request.getEmail(),
                    request.getPassword(), request.getPhone());
            User savedUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers(
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String password) {

        if (email != null && password != null) {
            // Login endpoint compatibility
            Optional<User> user = userService.authenticateUser(email, password);
            if (user.isPresent()) {
                return ResponseEntity.ok(List.of(toResponse(user.get())));
            } else {
                return ResponseEntity.ok(List.of());
            }
        } else if (email != null) {
            // Recovery endpoint compatibility
            List<User> users = userService.findByEmailForRecovery(email);
            List<UserResponse> responses = users.stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } else {
            // Get all users
            List<User> users = userService.getAllUsers();
            List<UserResponse> responses = users.stream()
                    .map(this::toResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(u -> ResponseEntity.ok(toResponse(u)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id,
                                                   @Valid @RequestBody UserRequest request) {
        try {
            User user = new User(id, request.getName(), request.getEmail(),
                    request.getPassword(), request.getPhone());
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(toResponse(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail(),
                user.getPhone());
    }
}

