package com.example.d5_mvc_jsp_quiz.exception.type;

public class InvalidCredentialsException extends RuntimeException {
  public InvalidCredentialsException(String message) {
    super(String.format("INVALID CREDENTIALS: %s", message));
  }
}
