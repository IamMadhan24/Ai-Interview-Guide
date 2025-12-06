package com.madhan.interviewcoach.dto;

import lombok.Data;

@Data
public class AnswerRequest {
    private String question;
    private String answer;
    private String role;
}
