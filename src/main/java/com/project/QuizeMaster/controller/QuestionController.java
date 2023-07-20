package com.project.QuizeMaster.controller;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.QuestionDto;
import com.project.QuizeMaster.entity.Question;
import com.project.QuizeMaster.service.QuestionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quize")
public class QuestionController {

    @Autowired
    QuestionServise questionServise;

    @PostMapping("/addQuestion")
    public Response addQuestion(@RequestBody QuestionDto questionDto) {
        return questionServise.addQuestion(questionDto);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionServise.getAllQuestion();
    }

    @GetMapping("/category")
    public Response getQuestionBYCategory(@RequestParam(required = true) String category, @RequestParam(required = true)String difficultyLevel){
        return questionServise.getQuestionByCategory(category,difficultyLevel);
    }

}
