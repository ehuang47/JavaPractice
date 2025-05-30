package com.example.d5_mvc_jsp_quiz.service;

import com.example.d5_mvc_jsp_quiz.exception.type.EntityNotFoundException;
import com.example.d5_mvc_jsp_quiz.repository.EntityRepository;

import java.util.List;

public abstract class EntityService<E,ID> {
  protected final EntityRepository<E, ID> repository;

  public EntityService(EntityRepository<E, ID> repository){
    this.repository = repository;
  }

  public ID save(E t){
    return repository.save(t);
  }

  public ID update(E t){
    return repository.update(t);
  }

  public E findById(ID id){
    return repository.findById(id).orElseThrow(EntityNotFoundException::new);
  }

  public List<E> findAll(){
    return repository.findAll();
  }
}
