package com.example.d5_mvc_jsp_quiz.repository;

import java.util.List;
import java.util.Optional;

public interface EntityRepository<E, ID> {
  default public ID save(E t) {return null;}

  default public Optional<E> findById(ID id) {return Optional.empty();}

  default public List<E> findAll() {
    return null;
  }
}
