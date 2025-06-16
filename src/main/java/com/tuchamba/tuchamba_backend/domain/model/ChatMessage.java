package com.tuchamba.tuchamba_backend.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private Long id;
    private String sender; // "user" or "bot"
    private String receiver;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessage(String sender, String content, @NotBlank(message = "Content is required") String requestContent) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
