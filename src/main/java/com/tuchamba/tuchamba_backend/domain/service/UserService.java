package com.tuchamba.tuchamba_backend.domain.service;

import com.tuchamba.tuchamba_backend.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> authenticateUser(String email, String password);
    Optional<User> getUserById(Long id);
    User updateUser(User user);
    List<User> getAllUsers();
    boolean existsByEmail(String email);
    List<User> findByEmailForRecovery(String email);
}
