package com.project.QuizeMaster.service;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.Request;
import com.project.QuizeMaster.entity.QuestionWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    Response createQuiz(String category, int numQ, String title);

    Response getQuizById(Long id);

    Response getQuiz(Long id);

    ResponseEntity<List<QuestionWrapper>> getQuizAuestionsById(Long id);

    Response calculateResult(Long id, List<Request> request);
}
