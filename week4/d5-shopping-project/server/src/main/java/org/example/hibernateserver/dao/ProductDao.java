package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends AbstractDao<Product> {
  public ProductDao() {
    settClass(Product.class);
  }
}