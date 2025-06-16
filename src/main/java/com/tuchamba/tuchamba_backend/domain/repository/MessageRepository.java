package com.tuchamba.tuchamba_backend.domain.repository;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import java.util.List;
import java.util.Optional;

public interface MessageRepository {
    ChatMessage save(ChatMessage message);
    Optional<ChatMessage> findById(Long id);
    List<ChatMessage> findAll();
    List<ChatMessage> findBySender(String sender);
    List<ChatMessage> findByReceiver(String receiver);
    List<ChatMessage> findBySenderAndReceiver(String sender, String receiver);
    List<ChatMessage> findByUserConversation(String user1, String user2);
    void deleteById(Long id);
}


