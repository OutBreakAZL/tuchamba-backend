package com.tuchamba.tuchamba_backend.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessageRequest {
    @NotBlank(message = "Sender is required")
    @Email(message = "Sender should be a valid email")
    private String sender;

    @NotBlank(message = "Receiver is required")
    @Email(message = "Receiver should be a valid email")
    private String receiver;

    @NotBlank(message = "Content is required")
    private String content;
}
