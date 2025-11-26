package com.madhan.interviewcoach.service;

import com.madhan.interviewcoach.dto.QuestionResponse;
import com.madhan.interviewcoach.dto.ResumeResponse;
import com.madhan.interviewcoach.util.PdfGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

     // QUESTIONS REPORT
    public byte[] generateQuestionsReport(QuestionResponse data) throws Exception {

        String title = "Interview Questions Report";
        String subtitle =
                "Topic: " + (data.getTopic() == null ? "-" : data.getTopic()) +
                        "  |  Difficulty: " + (data.getDifficulty() == null ? "-" : data.getDifficulty());

        List<String> questions = data.getQuestions() != null ? data.getQuestions() : new ArrayList<>();

        return PdfGenerator.generateQuestionsPdf(title, subtitle, questions);
    }

     // RESUME REPORT
    public byte[] generateResumeReport(ResumeResponse data) throws Exception {

        String title = "Resume Analysis Report";
        String summary = data.getSummary() != null ? data.getSummary() : "";

        List<String> strengths = data.getStrengths() != null ? data.getStrengths() : new ArrayList<>();
        List<String> weaknesses = data.getWeaknesses() != null ? data.getWeaknesses() : new ArrayList<>();
        List<String> improvements = data.getSuggestedImprovements() != null ? data.getSuggestedImprovements() : new ArrayList<>();

        return PdfGenerator.generateResumeReport(title, summary, strengths, weaknesses, improvements);
    }
}
