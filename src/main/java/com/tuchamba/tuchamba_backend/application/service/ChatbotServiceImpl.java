package com.tuchamba.tuchamba_backend.application.service;

import com.tuchamba.tuchamba_backend.domain.model.ChatMessage;
import com.tuchamba.tuchamba_backend.domain.service.ChatbotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ChatbotServiceImpl implements ChatbotService {

    private final List<String> motivationalPhrases = Arrays.asList(
            "Keep going, you are doing great!",
            "Every effort counts, don't give up!",
            "Believe in yourself, new opportunities are coming!",
            "Your perseverance will pay off!",
            "Remember why you started!",
            "You're stronger than you think!",
            "This challenge is making you grow!",
            "Success is just around the corner!",
            "Your hard work will be rewarded!",
            "Stay positive, better days are ahead!"
    );

    private final List<String> negativeKeywords = Arrays.asList(
            "frustrated", "sad", "tired", "lost", "discouraged",
            "depressed", "anxious", "worried", "stressed", "overwhelmed",
            "hopeless", "disappointed", "angry", "upset", "confused"
    );

    private final Random random = new Random();

    @Override
    public ChatMessage generateBotReply(String userMessage) {
        String content;

        if (isNegativeMessage(userMessage)) {
            content = getRandomMotivationalPhrase();
        } else {
            content = "I'm here to support you. Can you tell me more about how you're feeling?";
        }

        return new ChatMessage("bot", content);
    }

    @Override
    public boolean isNegativeMessage(String message) {
        String messageLower = message.toLowerCase();
        return negativeKeywords.stream()
                .anyMatch(messageLower::contains);
    }

    private String getRandomMotivationalPhrase() {
        int index = random.nextInt(motivationalPhrases.size());
        return motivationalPhrases.get(index);
    }
}
