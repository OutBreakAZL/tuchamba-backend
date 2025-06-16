package com.tuchamba.tuchamba_backend.domain.service;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import java.util.List;
import java.util.Optional;

public interface ChatbotService {
    ChatMessage sendMessage(ChatMessage message);
    Optional<ChatMessage> getMessageById(Long id);
    List<ChatMessage> getAllMessages();
    List<ChatMessage> getMessagesBySender(String sender);
    List<ChatMessage> getMessagesByReceiver(String receiver);
    List<ChatMessage> getConversation(String user1, String user2);
    List<ChatMessage> getUserMessages(String userEmail);
    void deleteMessage(Long id);
}
