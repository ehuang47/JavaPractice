package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.watchlist.WatchlistQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class WatchlistDao extends AbstractDao<Watchlist, WatchlistQueryDto> {
  public WatchlistDao() {
    settClass(Watchlist.class);
  }

  @Override
  protected Predicate buildPredicate(CriteriaBuilder cb, Root<Watchlist> root, WatchlistQueryDto filters) {
    Predicate p = cb.conjunction();

    String userId = filters.getUserId();
    if (userId != null && !userId.trim().isEmpty()) {
      p = cb.and(p, root.get("userId").in(userId));
    }
    String productId = filters.getProductId();
    if (productId != null && !productId.trim().isEmpty()) {
      p = cb.and(p, root.get("productId").in(productId));
    }

    return p;
  }
}
