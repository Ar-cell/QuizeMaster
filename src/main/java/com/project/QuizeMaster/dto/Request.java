package com.project.QuizeMaster.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Request {
    private Long id;
    private String response;
}
