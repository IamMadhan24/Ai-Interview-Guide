package com.madhan.interviewcoach.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.madhan.interviewcoach.client.GroqClient;
import com.madhan.interviewcoach.dto.ResumeResponse;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResumeService {

    private final GroqClient groqClient;
    private final ObjectMapper mapper;

    public ResumeService(GroqClient groqClient, ObjectMapper mapper) {
        this.groqClient = groqClient;
        this.mapper = mapper;
    }

    public ResumeResponse evaluateResume(MultipartFile file) throws Exception {

        // Extract text from PDF
        byte[] pdfBytes = file.getBytes();
        String text;

        try (PDDocument doc = Loader.loadPDF(pdfBytes)) {
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(doc);
        }

        // LLM prompt
        String system = """
        You are an expert Resume Analyzer.
        STRICT RULES:
        - Identify domain only from resume text.
        - Do NOT hallucinate any domain or technology not present in the resume.
        - Feedback must be relevant to freshers only.
        - No suggestions like Agile, Cloud, DevOps unless present in the resume.
        - Output ONLY VALID JSON with these fields:
          summary, strengths, weaknesses, suggestedImprovements
        """;

        String user = "Analyze this resume text:\n" + text;

        var messages = List.of(
                Map.of("role", "system", "content", system),
                Map.of("role", "user", "content", user)
        );

        // RAW LLM reply
        String reply = groqClient.generateChatCompletion(messages, 800);
        System.out.println("RAW LLM OUTPUT:\n" + reply);

        // CLEAN JSON FROM REPLY
        String cleaned = extractJson(reply);
        JsonNode root = mapper.readTree(cleaned);

        ResumeResponse response = new ResumeResponse();
        response.setSummary(root.path("summary").asText(""));

        // Parse list fields safely
        response.setStrengths(getSafeList(root, "strengths"));
        response.setWeaknesses(getSafeList(root, "weaknesses"));

        // FIX: Parse suggested improvements consistently
        List<String> improvementsList = getSafeList(root, "suggestedImprovements");
        if (improvementsList.isEmpty()) {
            improvementsList = getSafeList(root, "suggested_improvements");
        }
        response.setSuggestedImprovements(improvementsList);

        return response;
    }

    // Extract ONLY JSON object from LLM output
    private String extractJson(String input) {
        int start = input.indexOf('{');
        int end = input.lastIndexOf('}');
        if (start >= 0 && end > start) {
            return input.substring(start, end + 1);
        }
        return "{}";
    }

    // Ensure list is ALWAYS a List<String>
    private List<String> getSafeList(JsonNode root, String key) {
        JsonNode node = root.path(key);
        List<String> result = new ArrayList<>();

        if (node.isArray()) {
            node.forEach(n -> result.add(n.asText()));
        } else if (node.isObject()) {
            node.fields().forEachRemaining(entry ->
                    result.add(entry.getValue().asText())
            );
        } else if (node.isTextual()) {
            String v = node.asText();
            if (!v.isBlank()) result.add(v);
        }

        return result;
    }
}
