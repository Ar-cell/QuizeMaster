package com.project.QuizeMaster.dto;

import com.project.QuizeMaster.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {
    private Long id;
    private  String title;
    private List<Question> questions;
}
