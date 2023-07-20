package com.project.QuizeMaster.repository;

import com.project.QuizeMaster.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    Optional<Question> findByQuestion(String question);

    List<Question> findByCategoryAndDifficultyLevel(String category,String difficultyLevel);

//    @Query(value = "select * from question_table qt where qt.category=:category ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    @Query(value = "select * from question_table qt where qt.category=:category ORDER BY rand() LIMIT :numQ",nativeQuery = true)
    List<Question> finfRendomQuestionsByCategory(String category, int numQ);
}
