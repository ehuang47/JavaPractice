package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
    private int id;
    private int userId;
    private int quizId;
    private String startTime;
    private String endTime;
}
