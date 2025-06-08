package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.domain.enums.OrderStatus;
import org.example.hibernateserver.dto.order.OrderProductDto;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderDao extends AbstractDao<Order, OrderQueryDto> {
  public OrderDao() {
    settClass(Order.class);
  }

  @Override
  protected Predicate buildPredicate(CriteriaBuilder cb, Root<Order> root, OrderQueryDto filters) {
    Predicate p = cb.conjunction();

    String statusFilter = filters.getStatus();
    if (statusFilter != null) {
      List<OrderStatus> statusList = Arrays.stream(statusFilter.split(","))
        .map(OrderStatus::valueOf)
        .toList();
      p = cb.and(p, root.get("status").in(statusList));
    }

    return p;
  }
  @Override
  protected List<javax.persistence.criteria.Order> buildOrders(CriteriaBuilder cb, Root<Order> root, OrderQueryDto filters) {
    List<javax.persistence.criteria.Order> orders = new ArrayList<>();

    String sortQuery = filters.getSortBy();
    if (sortQuery != null) {
      String[] sortParts = sortQuery.split(",");
      for (String part: sortParts) {
        part = part.trim();
        boolean asc = true;
        String columnName;
        if (part.startsWith("-")) {
          asc = false;
          columnName = part.substring(1);
        } else if (part.startsWith("+")) {
          columnName = part.substring(1);
        } else {
          columnName = part;
        }
        orders.add(asc ? cb.asc(root.get(columnName)) : cb.desc(root.get(columnName)));
      }
    }

    return orders;
  }

public List<OrderProductDto> getOrderProductsByOrderId(Long orderId) {
  String jpql = """
      SELECT new org.example.hibernateserver.dto.order.OrderProductDto(
                  p.id as productId,
                  p.name,
                  p.description,
                  p.quantity,
                  p.retailPrice,
                  p.wholesalePrice,
                  oi.quantity as orderItemQuantity
              )
              FROM OrderItem oi, Product p
              WHERE oi.productId = p.id
              AND oi.orderId = :orderId
      """;
  return sessionFactory.getCurrentSession()
    .createQuery(jpql, OrderProductDto.class)
    .setParameter("orderId", orderId)
    .getResultList();
  }
}