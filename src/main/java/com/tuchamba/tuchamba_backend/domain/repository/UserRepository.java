package com.tuchamba.tuchamba_backend.domain.repository;

import com.tuchamba.tuchamba_backend.domain.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findAll();
    void deleteById(Long id);
    boolean existsByEmail(String email);
}
