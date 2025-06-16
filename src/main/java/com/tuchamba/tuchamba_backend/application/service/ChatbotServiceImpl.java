package com.tuchamba.tuchamba_backend.application.service;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import com.tuchamba.tuchamba_backend.domain.repository.MessageRepository;
import com.tuchamba.tuchamba_backend.domain.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ChatbotServiceImpl implements ChatbotService {

    private final MessageRepository messageRepository;

    @Autowired
    public ChatbotServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public ChatMessage sendMessage(ChatMessage message) {
        return messageRepository.save(message);
    }

    @Override
    public Optional<ChatMessage> getMessageById(Long id) {
        return messageRepository.findById(id);
    }

    @Override
    public List<ChatMessage> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public List<ChatMessage> getMessagesBySender(String sender) {
        return messageRepository.findBySender(sender);
    }

    @Override
    public List<ChatMessage> getMessagesByReceiver(String receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    @Override
    public List<ChatMessage> getConversation(String user1, String user2) {
        return messageRepository.findByUserConversation(user1, user2);
    }

    @Override
    public List<ChatMessage> getUserMessages(String userEmail) {
        List<ChatMessage> sentMessages = messageRepository.findBySender(userEmail);
        List<ChatMessage> receivedMessages = messageRepository.findByReceiver(userEmail);

        // Combine and sort by timestamp
        sentMessages.addAll(receivedMessages);
        return sentMessages.stream()
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .toList();
    }

    @Override
    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
