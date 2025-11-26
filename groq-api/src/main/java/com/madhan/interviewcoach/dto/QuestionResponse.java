package com.madhan.interviewcoach.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponse {
    private String topic;
    private String difficulty;
    private List<String> questions;
}
