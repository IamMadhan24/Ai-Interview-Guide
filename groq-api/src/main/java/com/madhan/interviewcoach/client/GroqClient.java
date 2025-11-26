package com.madhan.interviewcoach.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class GroqClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String apiUrl;
    private final String apiKey;
    private final String model;

    public GroqClient(RestTemplate restTemplate,
                      ObjectMapper objectMapper,
                      @Value("${groq.api.url}") String apiUrl,
                      @Value("${groq.api.key}") String apiKey,
                      @Value("${groq.model}") String model) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.apiUrl = apiUrl;
        this.apiKey = apiKey;
        this.model = model;
    }

    public String generateChatCompletion(List<Map<String, String>> messages, Integer maxTokens) throws Exception {

        ObjectNode body = objectMapper.createObjectNode();
        body.put("model", model);
        body.set("messages", objectMapper.valueToTree(messages));
        if (maxTokens != null) body.put("max_tokens", maxTokens);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(body), headers);

        ResponseEntity<String> resp = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new IllegalStateException("Groq returned error: " + resp.getStatusCode().value()
                    + "\nBody: " + resp.getBody());
        }

        JsonNode root = objectMapper.readTree(resp.getBody());
        JsonNode choices = root.path("choices");

        if (choices.isArray() && choices.size() > 0) {
            JsonNode first = choices.get(0);
            JsonNode msg = first.path("message");
            String content = msg.path("content").asText();

            if (content == null || content.isEmpty()) {
                content = first.path("text").asText();
            }

            return content;
        }

        throw new IllegalStateException("Groq returned no choices: " + resp.getBody());
    }
}
