package com.madhan.interviewcoach.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ResumeRequest {
    private MultipartFile file;
}
