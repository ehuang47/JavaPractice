package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
  private Long id;
  private Long userId;
  private Long quizId;
  private String startTime;
  private String endTime;
}
