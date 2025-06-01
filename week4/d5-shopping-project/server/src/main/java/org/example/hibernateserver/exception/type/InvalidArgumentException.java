package org.example.hibernateserver.exception.type;

public class InvalidArgumentException extends RuntimeException {
  public InvalidArgumentException(String message) {
    super(String.format("INVALID ARGUMENT: %s", message));
  }

  public InvalidArgumentException() {
    super("INVALID ARGUMENT");
  }
}
