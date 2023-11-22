package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Question {
    private int id;
    private int quizId;
    private int correctChoiceId;
    private String description;
    private List<Choice> choiceList;

}
