package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Quiz {
    private int id;
    private String category;
    private List<Question> questionList;
}
