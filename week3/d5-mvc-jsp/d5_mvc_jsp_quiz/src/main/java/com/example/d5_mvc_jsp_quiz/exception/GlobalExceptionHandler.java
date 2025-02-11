package com.example.d5_mvc_jsp_quiz.exception;

import com.example.d5_mvc_jsp_quiz.exception.type.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidArgumentException;
import com.example.d5_mvc_jsp_quiz.exception.type.InvalidCredentialsException;
import com.example.d5_mvc_jsp_quiz.exception.type.MethodNotAllowedException;
import com.example.d5_mvc_jsp_quiz.exception.type.NoResultsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex,
                                                         WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND.value(),
      ex.getMessage(),
      request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidArgumentException.class)
  public ResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException ex,
                                                          WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST.value(),
      ex.getMessage(),
      request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NoResultsException.class)
  public ResponseEntity<?> handleNoResultsException(NoResultsException ex,
                                                    WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NO_CONTENT.value(),
      ex.getMessage(),
      request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NO_CONTENT);
  }

  @ExceptionHandler(InvalidCredentialsException.class)
  public ResponseEntity<?> handleInvalidCredentialsException(InvalidCredentialsException ex,
                                                             WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.UNAUTHORIZED.value(),
      ex.getMessage(),
      request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(MethodNotAllowedException.class)
  public ResponseEntity<?> handleMethodNotAllowedException(MethodNotAllowedException ex,
                                                             WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(HttpStatus.METHOD_NOT_ALLOWED.value(),
      ex.getMessage(),
      request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.METHOD_NOT_ALLOWED);
  }

}
