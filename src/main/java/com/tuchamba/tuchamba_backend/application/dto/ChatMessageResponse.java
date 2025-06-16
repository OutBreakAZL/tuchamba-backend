package com.tuchamba.tuchamba_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageResponse {
    private String sender;
    private String content;
    private String timestamp;

    public ChatMessageResponse(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }
}
