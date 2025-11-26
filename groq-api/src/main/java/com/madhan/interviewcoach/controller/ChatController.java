package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.dto.ChatRequest;
import com.madhan.interviewcoach.dto.ChatResponse;
import com.madhan.interviewcoach.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<?> chat(@RequestBody ChatRequest request) {
        try {
            ChatResponse resp = chatService.chat(request.getMessage());
            return ResponseEntity.ok(resp);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body("Chatbot error: " + e.getMessage());
        }
    }
}
