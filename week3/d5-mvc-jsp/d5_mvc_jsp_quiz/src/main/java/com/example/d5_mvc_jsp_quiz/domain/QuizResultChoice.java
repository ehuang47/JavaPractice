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
public class QuizResultChoice {
  private Long id;
  private Long quizResultId;
  private Long questionId;
  private Long choiceId;
}
