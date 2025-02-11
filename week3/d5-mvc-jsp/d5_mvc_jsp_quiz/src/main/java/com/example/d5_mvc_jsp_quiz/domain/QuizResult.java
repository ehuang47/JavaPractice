package com.example.d5_mvc_jsp_quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuizResult {
  private Long id;
  private Long quizId;
  private String dateStarted;
  private String dateSubmitted;
  private Long userId;
  private List<QuizResultChoice> quizResultChoiceList;
}
