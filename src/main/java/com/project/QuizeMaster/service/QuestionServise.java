package com.project.QuizeMaster.service;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.QuestionDto;
import com.project.QuizeMaster.entity.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface QuestionServise {

     Response addQuestion(@RequestBody QuestionDto questionDto);

     ResponseEntity<List<Question>> getAllQuestion();

     Response getQuestionByCategory(String category,String difficultyLevel);
}
