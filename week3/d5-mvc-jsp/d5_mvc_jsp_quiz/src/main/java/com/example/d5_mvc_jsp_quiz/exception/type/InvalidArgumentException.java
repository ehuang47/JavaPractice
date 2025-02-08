package com.example.d5_mvc_jsp_quiz.exception.type;

public class InvalidArgumentException extends RuntimeException {
  public InvalidArgumentException(String message) {
    super(String.format("INVALID ARGUMENT: %s", message));
  }
}
