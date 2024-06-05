package com.example.d5_mvc_jsp_quiz.repository;

import java.util.List;
import java.util.Optional;

public interface ObjectRepository<T> {
  public Long save(T t);

  public Optional<T> findById(Long id);

  public List<T> findAll();
//  public T findByName(String name);
//  public T delete(int id);
}
