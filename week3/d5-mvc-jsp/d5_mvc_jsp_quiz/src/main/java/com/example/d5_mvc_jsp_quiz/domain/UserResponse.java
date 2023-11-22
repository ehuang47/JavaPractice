package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private int id;
    private int userId;
    private int quizResultId;
    private int questionId;
    private int selectedChoiceId;
}
