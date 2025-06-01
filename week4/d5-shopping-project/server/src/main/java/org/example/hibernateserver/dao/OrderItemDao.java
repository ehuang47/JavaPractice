package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.domain.enums.OrderStatus;
import org.example.hibernateserver.dto.orderitem.OrderItemQueryDto;
import org.example.hibernateserver.dto.orderitem.MostPurchasedItemDto;
import org.example.hibernateserver.dto.orderitem.RecentlyPurchasedItemDto;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@Repository
public class OrderItemDao extends AbstractDao<OrderItem, OrderItemQueryDto> {
  public OrderItemDao() {
    settClass(OrderItem.class);
  }

  @Override
  protected Predicate buildPredicate(CriteriaBuilder cb, Root<OrderItem> root, OrderItemQueryDto filters) {
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

  public List<MostPurchasedItemDto> findMostPurchased(OrderItemQueryDto orderItemQueryDto) {
    String jpql = """
            SELECT new org.example.hibernateserver.dto.orderitem.MostPurchasedItemDto(
                i.productId,
                SUM(i.quantity) as totalQuantity
            )
            FROM OrderItem i
            JOIN i.order o
            WHERE o.status <> :orderStatus
            GROUP BY i.productId
            ORDER BY SUM(i.quantity) DESC
        """;

    return getCurrentSession().createQuery(jpql, MostPurchasedItemDto.class)
      .setParameter("orderStatus", OrderStatus.CANCELED)
      .setMaxResults(orderItemQueryDto.getPageSize())
      .getResultList();
  }

  public List<RecentlyPurchasedItemDto> findRecentPurchases(OrderItemQueryDto orderItemQueryDto) {
    String jpql = """
            SELECT new org.example.hibernateserver.dto.orderitem.RecentlyPurchasedItemDto(
                i.productId,
                i.quantity,
                o.datePlaced as datePurchased
            )
            FROM OrderItem i
            JOIN i.order o
            WHERE o.status <> :orderStatus
            ORDER BY o.datePlaced DESC
        """;

    return getCurrentSession().createQuery(jpql, RecentlyPurchasedItemDto.class)
      .setParameter("orderStatus", OrderStatus.CANCELED)
      .setMaxResults(orderItemQueryDto.getPageSize())
      .getResultList();
  }
}
