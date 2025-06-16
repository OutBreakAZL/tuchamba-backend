package com.tuchamba.tuchamba_backend.infrastructure.repository;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import com.tuchamba.tuchamba_backend.domain.repository.MessageRepository;
import com.tuchamba.tuchamba_backend.infrastructure.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageRepository {

    private final MessageJpaRepository jpaRepository;

    @Autowired
    public MessageRepositoryImpl(MessageJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        MessageEntity entity = toEntity(chatMessage);
        MessageEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<ChatMessage> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<ChatMessage> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> findBySender(String sender) {
        return jpaRepository.findBySender(sender)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> findByReceiver(String receiver) {
        return jpaRepository.findByReceiver(receiver)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> findBySenderAndReceiver(String sender, String receiver) {
        return jpaRepository.findBySenderAndReceiver(sender, receiver)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatMessage> findByUserConversation(String user1, String user2) {
        return jpaRepository.findByUserConversation(user1, user2)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    // Mapper methods
    private MessageEntity toEntity(ChatMessage message) {
        if (message.getId() != null) {
            return new MessageEntity(message.getId(), message.getSender(),
                    message.getReceiver(), message.getContent(), message.getTimestamp());
        } else {
            return new MessageEntity(message.getSender(), message.getReceiver(),
                    message.getContent());
        }
    }

    private ChatMessage toDomain(MessageEntity entity) {
        return new ChatMessage(entity.getId(), entity.getSender(), entity.getReceiver(),
                entity.getContent(), entity.getTimestamp());
    }
}
