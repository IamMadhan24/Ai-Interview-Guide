package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.dto.ResumeResponse;
import com.madhan.interviewcoach.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/resume")
@CrossOrigin
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/evaluate")
    public ResponseEntity<?> evaluate(@RequestParam("file") MultipartFile file) {
        try {
            ResumeResponse response = resumeService.evaluateResume(file);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to evaluate resume: " + ex.getMessage());
        }
    }
}
