package com.madhan.interviewcoach.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResumeResponse {
    private String summary;
    private List<String> strengths;
    private List<String> weaknesses;
    private List<String> suggestedImprovements;
}
