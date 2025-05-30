package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;
import org.example.hibernateserver.dto.common.EntityMapper;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public abstract class AbstractService<E,D> {
  private final AbstractDao<E> abstractDao;
  protected final EntityMapper<E,D> entityMapper;

  public AbstractService(AbstractDao<E> abstractDao, EntityMapper<E,D> entityMapper) {
    this.abstractDao = abstractDao;
    this.entityMapper = entityMapper;
  }

  @Transactional
  public List<D> getAll() {
    return entityMapper.toDtoList(abstractDao.getAll());
  }

  @Transactional
  public D findById(int id) {
    return entityMapper.toDto(abstractDao.findById(id));
  }

  @Transactional
  public void add(D item) {
    abstractDao.add(entityMapper.toEntity(item));
  }

  public E load(int id) {
    return abstractDao.load(id).orElseThrow(() -> new EntityNotFoundException(id + " not found"));
  }
}
