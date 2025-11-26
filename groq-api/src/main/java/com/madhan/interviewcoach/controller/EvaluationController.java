package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.dto.EvaluationRequest;
import com.madhan.interviewcoach.dto.EvaluationResponse;
import com.madhan.interviewcoach.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/evaluate")
@CrossOrigin
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<EvaluationResponse> evaluate(@RequestBody EvaluationRequest request) {
        try {
            EvaluationResponse resp = evaluationService.evaluate(request);
            return ResponseEntity.ok(resp);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
