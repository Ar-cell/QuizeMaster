package com.project.QuizeMaster.serviceImpl;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.QuizDto;
import com.project.QuizeMaster.dto.QuizList;
import com.project.QuizeMaster.dto.Request;
import com.project.QuizeMaster.entity.Question;
import com.project.QuizeMaster.entity.QuestionWrapper;
import com.project.QuizeMaster.entity.Quiz;
import com.project.QuizeMaster.repository.QuestionRepository;
import com.project.QuizeMaster.repository.QuizRepository;
import com.project.QuizeMaster.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    public static final String SUCCESS="SUCCESS";
    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Response createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionRepository.finfRendomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new Response(SUCCESS,quiz, HttpStatus.OK);
    }

    @Override
    public Response getQuizById(Long id) {
        List<QuizDto> quizList = new ArrayList<>();
        List<Quiz> quizList1 = quizRepository.findAllById(Collections.singleton(id));
        if(!CollectionUtils.isEmpty(quizList1)){
           for(Quiz quiz:quizList1){
               QuizDto quizDto = new QuizDto() ;
               quizDto.setId(quiz.getId());
               quizDto.setTitle(quiz.getTitle());
               quizDto.setQuestions(quiz.getQuestions());
               quizList.add(quizDto);
           }
           return  new Response(SUCCESS,quizList,HttpStatus.OK);
        }
        return new Response("Quiz not found for this id",HttpStatus.BAD_REQUEST);
    }

    @Override
    public Response getQuiz(Long id) {
        List<QuizList> quizList = new ArrayList<>();
        List<Quiz> quizList1 = quizRepository.findAllById(Collections.singleton(id));
        if(!CollectionUtils.isEmpty(quizList1)) {
            for (Quiz quiz : quizList1) {
                QuizList quizDto = new QuizList();
                quizDto.setId(quiz.getId());
                }
                return new Response(SUCCESS, questionRepository.findAllById(Collections.singleton(id)), HttpStatus.OK);
            }
            return new Response("Quiz not found for this id", HttpStatus.BAD_REQUEST);
        }

    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuizAuestionsById(Long id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionWrappers = new ArrayList<>();
        for(Question question: questions){
            QuestionWrapper questionWrapper = new QuestionWrapper(question.getId(),question.getQuestion(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    @Override
    public Response calculateResult(Long id, List<Request> request) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Question> questionList = quiz.getQuestions();
        int right=0;
        int i =0;
        for(Request req :request){
            if(req.getResponse().equals(questionList.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new Response(SUCCESS,right,HttpStatus.OK);
    }
}
