package com.project.QuizeMaster.serviceImpl;

import com.project.QuizeMaster.config.Response;
import com.project.QuizeMaster.dto.QuestionDto;
import com.project.QuizeMaster.entity.Question;
import com.project.QuizeMaster.repository.QuestionRepository;
import com.project.QuizeMaster.service.QuestionServise;
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
public class QuestionServiceImpl implements QuestionServise {

    public static final String SUCCESS = "SUCCESS";
    @Autowired
    QuestionRepository questionRepository;
    @Override
    public Response addQuestion(QuestionDto questionDto) {
        Response response = new Response();
        Optional<Question> questonOptional = questionRepository.findByQuestion(questionDto.getQuestion());
        if(questonOptional.isPresent()){
            return response.builder().status(HttpStatus.BAD_REQUEST).code(HttpStatus.BAD_REQUEST.value()).message("This Question is already exist !").build();
        }
        Question question = Question.builder().question(questionDto.getQuestion()).option1(questionDto.getOption1())
                .option2(questionDto.getOption2()).option3(questionDto.getOption3()).option4(questionDto.getOption4())
                .difficultyLevel(questionDto.getDifficultyLevel()).category(questionDto.getCategory()).rightAnswer(questionDto.getRightAnswer()).build();
        questionRepository.save(question);
        return response.builder().status(HttpStatus.OK).code(HttpStatus.OK.value()).responseObject(question).message(SUCCESS).build();
    }

    @Override
    public ResponseEntity<List<Question>> getAllQuestion() {
        return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
    }

    @Override
    public Response getQuestionByCategory(String category,String difficultyLevel) {
        List<Question> questions = new ArrayList<>();
        List<Question> question = questionRepository.findByCategoryAndDifficultyLevel(category,difficultyLevel);
        if(CollectionUtils.isEmpty(question)){
            return  new Response("This category is not present !",category, HttpStatus.BAD_REQUEST);
        }
        for(Question question1 :question){
            Question question2 = Question.builder().id(question1.getId()).category(question1.getCategory())
                    .question(question1.getQuestion()).option1(question1.getOption1())
                    .option2(question1.getOption2()).option3(question1.getOption3()).option4(question1.getOption4())
                    .rightAnswer(question1.getRightAnswer()).difficultyLevel(question1.getDifficultyLevel()).build();
            questions.add(question2);
        }
        return new Response(SUCCESS,questions,HttpStatus.OK);
    }
}
