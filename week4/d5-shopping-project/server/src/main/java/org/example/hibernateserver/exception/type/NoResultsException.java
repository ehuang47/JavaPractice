package org.example.hibernateserver.exception.type;

public class NoResultsException extends RuntimeException {
  public NoResultsException() {
    super("No results were found.");
  }
}
