package com.project.QuizeMaster.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizList {
    private Long id;
    private String Question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    private String title;

    private String difficultylevel;



}
