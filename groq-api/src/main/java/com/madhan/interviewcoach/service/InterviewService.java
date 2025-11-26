package com.madhan.interviewcoach.service;

import com.madhan.interviewcoach.client.GroqClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InterviewService {

    private final GroqClient groqClient;

    public InterviewService(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public String generateAnswer(String question, String role) throws Exception {

        String systemPrompt =
                "You are an expert technical interviewer and trainer. " +
                        "When the user asks any question, your job is to give the BEST POSSIBLE ANSWER. " +
                        "Do NOT provide evaluation, scoring, summary, or areas to improve. " +
                        "Do NOT return JSON. Only return a clean, polished human answer.";

        String userPrompt =
                "Role: " + (role == null ? "candidate" : role) + "\n" +
                        "Question: " + question + "\n" +
                        "Give the best possible answer.";

        var messages = List.of(
                Map.of("role", "system", "content", systemPrompt),
                Map.of("role", "user", "content", userPrompt)
        );

        return groqClient.generateChatCompletion(messages, 800);
    }
}
