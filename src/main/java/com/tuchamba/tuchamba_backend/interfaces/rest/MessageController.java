// src/main/java/com/tuchamba/tuchamba_backend/interfaces/rest/MessageController.java
package com.tuchamba.tuchamba_backend.interfaces.rest;

import com.tuchamba.tuchamba_backend.application.dto.ChatMessageRequest;
import com.tuchamba.tuchamba_backend.application.dto.ChatMessageResponse;
import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import com.tuchamba.tuchamba_backend.domain.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

    private final ChatbotService messageService;

    @Autowired
    public MessageController(ChatbotService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<ChatMessageResponse> sendMessage(@Valid @RequestBody ChatMessageRequest request) {
        try {
            ChatMessage message = new ChatMessage(request.getSender(), request.getReceiver(), request.getContent());
            ChatMessage savedMessage = messageService.sendMessage(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(savedMessage));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ChatMessageResponse>> getMessages(
            @RequestParam(required = false) String sender,
            @RequestParam(required = false) String receiver,
            @RequestParam(required = false) String user1,
            @RequestParam(required = false) String user2,
            @RequestParam(required = false) String userEmail) {

        List<ChatMessage> messages;

        if (user1 != null && user2 != null) {
            // Get conversation between two users
            messages = messageService.getConversation(user1, user2);
        } else if (userEmail != null) {
            // Get all messages for a specific user (sent and received)
            messages = messageService.getUserMessages(userEmail);
        } else if (sender != null && receiver != null) {
            // Get messages from specific sender to specific receiver
            messages = messageService.getConversation(sender, receiver);
        } else if (sender != null) {
            // Get messages sent by specific user
            messages = messageService.getMessagesBySender(sender);
        } else if (receiver != null) {
            // Get messages received by specific user
            messages = messageService.getMessagesByReceiver(receiver);
        } else {
            // Get all messages
            messages = messageService.getAllMessages();
        }

        List<ChatMessageResponse> responses = messages.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatMessageResponse> getMessageById(@PathVariable Long id) {
        Optional<ChatMessage> message = messageService.getMessageById(id);
        return message.map(m -> ResponseEntity.ok(toResponse(m)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        try {
            messageService.deleteMessage(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<ChatMessageResponse>> getConversation(
            @RequestParam String user1,
            @RequestParam String user2) {

        List<ChatMessage> messages = messageService.getConversation(user1, user2);
        List<ChatMessageResponse> responses = messages.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<List<ChatMessageResponse>> getUserMessages(@PathVariable String userEmail) {
        List<ChatMessage> messages = messageService.getUserMessages(userEmail);
        List<ChatMessageResponse> responses = messages.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    private ChatMessageResponse toResponse(ChatMessage message) {
        return new ChatMessageResponse(message.getId(), message.getSender(),
                message.getReceiver(), message.getContent(), message.getTimestamp());
    }
}
