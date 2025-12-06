package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/interview")
@CrossOrigin
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/answer")
    public ResponseEntity<String> getInterviewAnswer(@RequestBody Map<String, String> body) {
        try {
            String question = body.get("question");
            String role = body.getOrDefault("role", "candidate");

            String answer = answerService.generateAnswer(question, role);

            return ResponseEntity.ok(answer);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().body("Error: " + ex.getMessage());
        }
    }
}
