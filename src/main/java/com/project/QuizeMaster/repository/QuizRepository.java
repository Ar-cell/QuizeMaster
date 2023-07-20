package com.project.QuizeMaster.repository;

import com.project.QuizeMaster.entity.Question;
import com.project.QuizeMaster.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
//    List<Question> findByQuestionId();

    @Query(value = "select * from quiz_table qt inner join quiz_table_questions qtq where qt.id=qtq.quiz_id=?1",nativeQuery = true)
    List<Quiz> findAllById(Long id);
}
