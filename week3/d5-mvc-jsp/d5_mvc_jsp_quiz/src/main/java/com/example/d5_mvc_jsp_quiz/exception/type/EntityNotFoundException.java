package com.example.d5_mvc_jsp_quiz.exception.type;

import com.example.d5_mvc_jsp_quiz.exception.EntityType;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(EntityType entity, Long id) {
    super(String.format("%s with id %d could not be found.", entity.name(), id));
  }

  public EntityNotFoundException(EntityType entity, String name) {
    super(String.format("%s with name %s could not be found.", entity.name(), name));
  }

  public EntityNotFoundException() {
    super("Not found.");
  }
}

