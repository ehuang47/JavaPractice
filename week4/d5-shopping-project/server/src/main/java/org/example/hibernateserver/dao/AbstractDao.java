package org.example.hibernateserver.dao;

import org.example.hibernateserver.dto.common.QueryDto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<E, Q extends QueryDto> {
  @Autowired
  protected SessionFactory sessionFactory;

  protected Class<E> tClass;

  protected final void settClass(final Class<E> tClass) {
    this.tClass = tClass;
  }

  protected Session getCurrentSession() {
    return sessionFactory.getCurrentSession();
  }

  public List<E> getAll(Q filters) {
    Session session = getCurrentSession();
    CriteriaBuilder builder = session.getCriteriaBuilder();
    CriteriaQuery<E> criteriaQuery = builder.createQuery(tClass);
    Root<E> root = criteriaQuery.from(tClass);

    Predicate filter = buildPredicate(builder, root, filters);
    List<Order> orders =  buildOrders(builder, root, filters);
    criteriaQuery.select(root).where(filter).orderBy(orders);

    return session.createQuery(criteriaQuery).getResultList();
  }

  protected Predicate buildPredicate(CriteriaBuilder cb, Root<E> root, Q filters) {
    return cb.conjunction();
  }

  protected List<Order> buildOrders(CriteriaBuilder cb, Root<E> root, Q filters) {
    return new ArrayList<>();
  }

  public E findById(Long id) {
    return getCurrentSession().get(tClass, id);
  }

  public void add(E entity) {
    getCurrentSession().save(entity);
  }

  public Optional<E> load(Long id) {
    final E entity = getCurrentSession().load(tClass, id);
    return Optional.ofNullable(entity);
  }
}
