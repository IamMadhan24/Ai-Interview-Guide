package com.madhan.interviewcoach.service;

import com.madhan.interviewcoach.dto.*;
import com.madhan.interviewcoach.util.PdfGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    // ========== QUESTIONS ==========
    public byte[] generateQuestionsReport(QuestionResponse data) throws Exception {
        String title = "Interview Questions Report";
        String subtitle = "Topic: " + data.getTopic() +
                " | Difficulty: " + data.getDifficulty();

        List<String> questions = data.getQuestions() != null ? data.getQuestions() : new ArrayList<>();

        return PdfGenerator.generateQuestionsPdf(title, subtitle, questions);
    }


    // ========== RESUME ==========
    public byte[] generateResumeReport(ResumeResponse data) throws Exception {
        return PdfGenerator.generateResumeReport(
                "Resume Analysis Report",
                data.getSummary(),
                data.getStrengths(),
                data.getWeaknesses(),
                data.getSuggestedImprovements()
        );
    }


    // ========== ANSWER REPORT ==========
    public byte[] generateAnswerReport(String question, String answer, String role) throws Exception {
        return PdfGenerator.generateAnswerPdf(question, answer, role);
    }


    // ========== EVALUATION REPORT ==========
    public byte[] generateEvaluationReport(EvaluationResponse data) throws Exception {
        return PdfGenerator.generateEvaluationPdf(
                data.getSummary(),
                data.getScore(),
                data.getAreasToImprove(),
                data.getSuggestedRevision()
        );
    }
}
