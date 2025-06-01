package org.example.hibernateserver.exception.type;

public class InvalidCredentialsException extends RuntimeException {
  public InvalidCredentialsException(String message) {
    super(String.format("INVALID CREDENTIALS: %s", message));
  }
}
