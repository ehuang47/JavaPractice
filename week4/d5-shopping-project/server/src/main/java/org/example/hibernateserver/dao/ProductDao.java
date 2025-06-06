package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProductDao extends AbstractDao<Product, ProductQueryDto> {
  public ProductDao() {
    settClass(Product.class);
  }

  @Override
  protected Predicate buildPredicate(CriteriaBuilder cb, Root<Product> root, ProductQueryDto filters) {
    Predicate p = cb.conjunction();

    Boolean inStockOnly = filters.getInStockOnly();
    if (Boolean.TRUE.equals(inStockOnly)) {
      p = cb.and(p, cb.greaterThan(root.get("quantity"), 0));
    }

    String id = filters.getId();
    if (id != null && !id.trim().isEmpty()) {
      List<Long> ids = Arrays.stream(id.split(","))
        .map(String::trim)
        .map(Long::parseLong)
        .toList();
      p = cb.and(p, root.get("id").in(ids));
    }

    return p;
  }
}