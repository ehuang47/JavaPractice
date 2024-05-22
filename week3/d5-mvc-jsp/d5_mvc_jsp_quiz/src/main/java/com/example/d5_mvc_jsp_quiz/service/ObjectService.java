package com.example.d5_mvc_jsp_quiz.service;

import java.util.List;

public interface ObjectService<T> {
  public void save(T t);

  public T findById(Long id);

  public List<T> findAll();
}
