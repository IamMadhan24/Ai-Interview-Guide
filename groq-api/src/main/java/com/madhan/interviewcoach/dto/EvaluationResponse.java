package com.madhan.interviewcoach.dto;

import lombok.Data;
import java.util.List;

@Data
public class EvaluationResponse {
    private String summary;
    private Integer score;
    private List<String> areasToImprove;
    private String suggestedRevision;
}
