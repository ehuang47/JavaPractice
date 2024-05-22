package com.example.d5_mvc_jsp_quiz.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDetails {
  private int statusCode;
  private String message;
  private String details;
}
