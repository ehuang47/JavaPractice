package com.example.d5_mvc_jsp_quiz.exception.type;

public class MethodNotAllowedException extends RuntimeException {
  public MethodNotAllowedException() {
    super("This method is not supported.");
  }
}

