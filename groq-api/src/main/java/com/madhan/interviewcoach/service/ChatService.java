package com.madhan.interviewcoach.service;

import com.madhan.interviewcoach.client.GroqClient;
import com.madhan.interviewcoach.dto.ChatResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ChatService {

    private final GroqClient groqClient;

    public ChatService(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public ChatResponse chat(String userMessage) throws Exception {

        String systemPrompt = """
                You are an expert Interview Assistant.
                Your job:
                - Answer any technical or interview-related question.
                - Keep responses simple, clean, and beginner-friendly.
                - Avoid unnecessary details.
                """;

        var messages = List.of(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", userMessage)
        );

        String reply = groqClient.generateChatCompletion(messages, 400);

        ChatResponse response = new ChatResponse();
        response.setReply(reply.trim());
        return response;
    }
}
