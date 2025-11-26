package com.madhan.interviewcoach.dto;

import lombok.Data;

@Data
public class QuestionRequest {
    private String topic;
    private String difficulty;
    private Integer count = 5;
}
