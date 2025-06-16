package com.tuchamba.tuchamba_backend.infrastructure.repository;

import com.tuchamba.tuchamba_backend.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password")
    List<UserEntity> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);

    boolean existsByEmail(String email);
}
