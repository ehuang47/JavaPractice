package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choice {
    private int id;
    private int questionId;
    private String description;
}
