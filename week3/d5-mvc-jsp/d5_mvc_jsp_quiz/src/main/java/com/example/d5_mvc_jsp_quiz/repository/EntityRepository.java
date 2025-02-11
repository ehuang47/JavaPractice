package com.example.d5_mvc_jsp_quiz.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EntityRepository<E, ID> {
  default public ID save(E t) {return null;}

  default public Optional<E> findById(ID id) {return Optional.empty();}

  default public List<E> findAll() {
    return null;
  }

  default public List<E> findAll(Map<String, Object> filters) {
    return null;
  }
//  public E findByName(String name);
//  public E delete(int id);
}
