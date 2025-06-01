package org.example.hibernateserver.exception.type;


public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(Long id) {
    super(String.format("Resource with id %d not found.",  id));
  }

  public EntityNotFoundException() {
    super("Not found.");
  }
}

