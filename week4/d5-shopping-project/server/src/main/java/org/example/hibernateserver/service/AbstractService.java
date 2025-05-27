package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

public abstract class AbstractService<T> {
  private final AbstractDao<T> abstractDao;

  public AbstractService(AbstractDao<T> abstractDao) {
    this.abstractDao = abstractDao;
  }

  @Transactional
  public List<T> getAll() {
    return abstractDao.getAll();
  }

  @Transactional
  public T findById(int id) {
    return abstractDao.findById(id);
  }

  @Transactional
  public void add(Collection<T> items) {
    for (T item : items) {
      abstractDao.add(item);
    }
  }

}
