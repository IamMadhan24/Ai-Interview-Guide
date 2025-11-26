package com.madhan.interviewcoach.service;

import com.madhan.interviewcoach.client.GroqClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionService {

    private final GroqClient groqClient;

    public QuestionService(GroqClient groqClient) {
        this.groqClient = groqClient;
    }

    public String generateQuestions(String topic, int count) throws Exception {

        String system = "You are an expert interviewer. Generate only a clean numbered list of interview questions. "
                + "Do NOT return JSON. Do NOT add backticks or code blocks.";

        String user = "Generate " + count + " interview questions for the topic: " + topic;

        var messages = List.of(
                Map.of("role", "system", "content", system),
                Map.of("role", "user", "content", user)
        );

        return groqClient.generateChatCompletion(messages, 500);
    }
}
