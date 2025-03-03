package com.example.d5_mvc_jsp_quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResultSummary {
  private Long id;
  private Long quizId;
  private String dateStarted;
  private String dateSubmitted;
  private Long userId;
  private String firstName;
  private String lastName;
  private String category;
  private int score;
}
