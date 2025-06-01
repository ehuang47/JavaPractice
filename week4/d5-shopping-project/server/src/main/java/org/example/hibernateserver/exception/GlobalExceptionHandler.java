package org.example.hibernateserver.exception;

import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.exception.type.EntityNotFoundException;
import org.example.hibernateserver.exception.type.InvalidArgumentException;
import org.example.hibernateserver.exception.type.InvalidCredentialsException;
import org.example.hibernateserver.exception.type.NoResultsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
      .body(DataResponse.failure(ex.getMessage()));
  }

  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException ex){
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(DataResponse.failure(ex.getMessage()));
  }

  @ExceptionHandler(NoResultsException.class)
  public ResponseEntity<?> handleNoResultsException(NoResultsException ex){
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
      .body(DataResponse.failure(ex.getMessage()));
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex){
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body(DataResponse.failure(ex.getMessage()));
  }
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> handleHttpMessageNotReadableException() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
      .body(DataResponse.failure("Invalid input format: Check field types in the request."));
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<?> handleHttpRequestMethodNotSupportedException() {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
      .body(DataResponse.failure("Method not allowed for this endpoint."));
  }
}
