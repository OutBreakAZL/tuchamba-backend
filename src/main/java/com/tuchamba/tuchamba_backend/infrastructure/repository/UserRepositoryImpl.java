package com.tuchamba.tuchamba_backend.infrastructure.repository;

import com.tuchamba.tuchamba_backend.domain.model.User;
import com.tuchamba.tuchamba_backend.domain.repository.UserRepository;
import com.tuchamba.tuchamba_backend.infrastructure.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    @Autowired
    public UserRepositoryImpl(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        UserEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public List<User> findByEmailAndPassword(String email, String password) {
        return jpaRepository.findByEmailAndPassword(email, password)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    // Mapper methods
    private UserEntity toEntity(User user) {
        if (user.getId() != null) {
            return new UserEntity(user.getId(), user.getName(), user.getEmail(),
                    user.getPassword(), user.getPhone());
        } else {
            return new UserEntity(user.getName(), user.getEmail(),
                    user.getPassword(), user.getPhone());
        }
    }

    private User toDomain(UserEntity entity) {
        return new User(entity.getId(), entity.getName(), entity.getEmail(),
                entity.getPassword(), entity.getPhone());
    }
}
