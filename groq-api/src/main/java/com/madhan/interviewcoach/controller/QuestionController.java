package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.dto.QuestionRequest;
import com.madhan.interviewcoach.dto.QuestionResponse;
import com.madhan.interviewcoach.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/questions")
@CrossOrigin
public class QuestionController {

    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<QuestionResponse> generate(@RequestBody QuestionRequest request) {

        try {
            String topic = request.getTopic();
            int count = request.getCount();
            String difficulty = request.getDifficulty();

            String raw = service.generateQuestions(topic, count);
            List<String> questions = raw.lines().toList();

            QuestionResponse resp = new QuestionResponse();
            resp.setTopic(topic);
            resp.setDifficulty(difficulty);
            resp.setQuestions(questions);

            return ResponseEntity.ok(resp);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
