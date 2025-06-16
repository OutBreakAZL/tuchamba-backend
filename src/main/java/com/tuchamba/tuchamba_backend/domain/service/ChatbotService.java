package com.tuchamba.tuchamba_backend.domain.service;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;

public interface ChatbotService {
    ChatMessage generateBotReply(String userMessage);
    boolean isNegativeMessage(String message);
}
