package com.tuchamba.tuchamba_backend.interfaces.rest;

import com.tuchamba.tuchamba_backend.application.dto.ChatMessageRequest;
import com.tuchamba.tuchamba_backend.application.dto.ChatMessageResponse;
import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import com.tuchamba.tuchamba_backend.domain.service.ChatbotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatbotController {

    private final ChatbotService chatbotService;

    @Autowired
    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }

    @PostMapping("/message")
    public ResponseEntity<ChatMessageResponse> sendMessage(@RequestBody ChatMessageRequest request) {
        try {
            ChatMessage botReply = chatbotService.generateBotReply(request.getMessage());
            ChatMessageResponse response = new ChatMessageResponse(
                    botReply.getSender(),
                    botReply.getContent(),
                    botReply.getTimestamp()
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Chatbot service is running");
    }
}
