package com.madhan.interviewcoach.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhan.interviewcoach.client.GroqClient;
import com.madhan.interviewcoach.dto.EvaluationRequest;
import com.madhan.interviewcoach.dto.EvaluationResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EvaluationService {

    private final GroqClient groqClient;
    private final ObjectMapper mapper;

    public EvaluationService(GroqClient groqClient, ObjectMapper mapper) {
        this.groqClient = groqClient;
        this.mapper = mapper;
    }

    public EvaluationResponse evaluate(EvaluationRequest req) throws Exception {

        String system = """
                You are an expert technical interviewer.

                Return ONLY a JSON object with exactly:
                - summary (string)
                - score (integer 0–10)
                - areasToImprove (array of strings)
                - suggestedRevision (string)

                DO NOT return any other text.
                DO NOT explain yourself.
                """;

        String user = "Question: " + safe(req.getQuestion()) +
                "\nCandidate Answer: " + safe(req.getCandidateAnswer()) +
                "\nEvaluate the answer and return the JSON.";

        var messages = List.of(
                Map.of("role", "system", "content", system),
                Map.of("role", "user", "content", user)
        );

        String modelReply = groqClient.generateChatCompletion(messages, 800);

        JsonNode root = extractJson(modelReply);

        EvaluationResponse resp = new EvaluationResponse();

        if (root != null && root.isObject()) {
            resp.setSummary(root.path("summary").asText(""));
            resp.setScore(root.path("score").asInt(0));

            if (root.has("areasToImprove") && root.path("areasToImprove").isArray()) {
                List<String> list = mapper.convertValue(
                        root.path("areasToImprove"),
                        mapper.getTypeFactory().constructCollectionType(List.class, String.class)
                );
                resp.setAreasToImprove(list);
            } else {
                resp.setAreasToImprove(Collections.emptyList());
            }

            resp.setSuggestedRevision(root.path("suggestedRevision").asText(""));
        } else {
            // fallback
            resp.setSummary("Invalid model output: " + safe(modelReply));
            resp.setScore(0);
            resp.setAreasToImprove(Collections.emptyList());
            resp.setSuggestedRevision("");
        }

        return resp;
    }

    private JsonNode extractJson(String text) {
        try {
            return mapper.readTree(text);
        } catch (Exception ignored) {}

        int s = text.indexOf('{');
        int depth = 0;

        for (int i = s; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                if (depth == 0) {
                    String json = text.substring(s, i + 1);
                    try {
                        return mapper.readTree(json);
                    } catch (Exception ignored2) {
                        return null;
                    }
                }
            }
        }

        return null;
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}
