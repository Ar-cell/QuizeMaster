package com.project.QuizeMaster.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name ="Question_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="question")
    private  String question;
    @Column(name="option1")
    private String option1;
    @Column(name="option2")
    private String option2;
    @Column(name="option3")
    private String option3;
    @Column(name="option4")
    private String option4;
    @Column(name="right_answer")
    private  String rightAnswer;
    @Column(name="category")
    private String category;
    @Column(name="difficulty_level")
    private String difficultyLevel;

}
