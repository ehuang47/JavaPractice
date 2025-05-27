package org.example.hibernateserver.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public abstract class AbstractDao<T> {
  @Autowired
  protected SessionFactory sessionFactory;

  protected Class<T> tClass;

  protected final void settClass(final Class<T> tClass) {
    this.tClass = tClass;
  }

  protected Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public List<T> getAll() {
    Session session = getCurrentSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
//    create query that returns objects of this tClass type
    CriteriaQuery<T> criteriaQuery = builder.createQuery(tClass);
//    return records from table/entity associated with tClass
    criteriaQuery.from(tClass);
    return session.createQuery(criteriaQuery).getResultList();
  }

  public T findById(int id) {
    return getCurrentSession().get(tClass, id);
  }

  public void add(T item) {
    getCurrentSession().save(item);
  }
}
