package com.example.d5_mvc_jsp_quiz.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
  private Long id;
  private Long userId;
  private Long quizResultId;
  private Long questionId;
  private Long selectedChoiceId;
}
