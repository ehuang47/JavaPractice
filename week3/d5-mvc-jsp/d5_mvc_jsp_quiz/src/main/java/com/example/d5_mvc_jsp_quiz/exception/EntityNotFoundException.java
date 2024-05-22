package com.example.d5_mvc_jsp_quiz.exception;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(EntityType entity, Long id) {
    super(String.format("%s with id %d could not be found.", entity.name(), id));
  }
}
