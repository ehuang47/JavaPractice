package org.example.hibernateserver.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E> {
  @Autowired
  protected SessionFactory sessionFactory;

  protected Class<E> tClass;

  protected final void settClass(final Class<E> tClass) {
    this.tClass = tClass;
  }

  protected Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public List<E> getAll() {
    Session session = getCurrentSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
//    create query that returns objects of this tClass type
    CriteriaQuery<E> criteriaQuery = builder.createQuery(tClass);
//    return records from table/entity associated with tClass
    criteriaQuery.from(tClass);
    return session.createQuery(criteriaQuery).getResultList();
  }

  public E findById(int id) {
    return getCurrentSession().get(tClass, id);
  }

  public void add(E entity) {
    getCurrentSession().save(entity);
  }

  public Optional<E> load(int id) {
    final E entity = getCurrentSession().load(tClass, id);
    return Optional.ofNullable(entity);
  }
}
