package com.project.QuizeMaster.controller;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.Request;
import com.project.QuizeMaster.entity.QuestionWrapper;
import com.project.QuizeMaster.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public Response cerateQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("/getQuiz/{id}")
    public Response getQuizById(@PathVariable Long id){
        return quizService.getQuizById(id);
    }

    @GetMapping("/quizQuestion/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Long id){
       return quizService.getQuizAuestionsById(id);
    }

    @GetMapping("/{id}")
    public Response getQuiz(@PathVariable Long id){
        return quizService.getQuiz(id);
    }

    @PostMapping("/submit/{id}")
    public Response submitQuiz(@PathVariable Long id, @RequestBody List<Request> request){
        return quizService.calculateResult(id,request);
    }
}
