package com.example.d5_mvc_jsp_quiz.exception;

public class NoResultsException extends RuntimeException {
  private static final String DEFAULT_MESSAGE = "No results were found for %s.";

  public NoResultsException(EntityType entity) {
    super(String.format(DEFAULT_MESSAGE, entity));
  }

  public NoResultsException(EntityType entity, String message) {
    super(String.format(String.format("%s %s", DEFAULT_MESSAGE, message), entity));
  }
}
