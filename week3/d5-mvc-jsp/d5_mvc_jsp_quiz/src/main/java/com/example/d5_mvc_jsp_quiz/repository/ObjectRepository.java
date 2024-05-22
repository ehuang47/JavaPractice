package com.example.d5_mvc_jsp_quiz.repository;

import java.util.List;

public interface ObjectRepository<T> {
  public void save(T t);

  public T findById(Long id);

  public List<T> findAll();
//  public T findByName(String name);
//  public T delete(int id);
}
