package com.project.QuizeMaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private  String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private  String rightAnswer;
    private String category;
    private String difficultyLevel;

}
