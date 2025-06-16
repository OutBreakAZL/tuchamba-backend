package com.tuchamba.tuchamba_backend.infrastructure.repository;

import com.tuchamba.tuchamba_backend.infrastructure.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageJpaRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySender(String sender);
    List<MessageEntity> findByReceiver(String receiver);
    List<MessageEntity> findBySenderAndReceiver(String sender, String receiver);

    @Query("SELECT m FROM MessageEntity m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp ASC")
    List<MessageEntity> findByUserConversation(@Param("user1") String user1, @Param("user2") String user2);

    @Query("SELECT m FROM MessageEntity m WHERE m.sender = :user OR m.receiver = :user ORDER BY m.timestamp DESC")
    List<MessageEntity> findByUserMessages(@Param("user") String user);
}
