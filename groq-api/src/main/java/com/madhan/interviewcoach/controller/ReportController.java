package com.madhan.interviewcoach.controller;

import com.madhan.interviewcoach.dto.*;
import com.madhan.interviewcoach.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/report")
@CrossOrigin
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    // ===== QUESTIONS =====
    @PostMapping("/questions")
    public ResponseEntity<byte[]> downloadQuestionsReport(@RequestBody QuestionResponse req) {
        try {
            byte[] pdf = reportService.generateQuestionsReport(req);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=questions_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    // ===== RESUME =====
    @PostMapping("/resume")
    public ResponseEntity<byte[]> downloadResumeReport(@RequestBody ResumeResponse req) {
        try {
            byte[] pdf = reportService.generateResumeReport(req);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=resume_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    // ===== ANSWER REPORT =====
    @PostMapping("/answer")
    public ResponseEntity<byte[]> downloadAnswerReport(@RequestBody AnswerRequest req) {
        try {
            byte[] pdf = reportService.generateAnswerReport(
                    req.getQuestion(),
                    req.getAnswer(),
                    req.getRole()
            );

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=answer_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }


    // ===== EVALUATION REPORT =====
    @PostMapping("/evaluation")
    public ResponseEntity<byte[]> downloadEvaluationReport(@RequestBody EvaluationResponse req) {
        try {
            byte[] pdf = reportService.generateEvaluationReport(req);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evaluation_report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
