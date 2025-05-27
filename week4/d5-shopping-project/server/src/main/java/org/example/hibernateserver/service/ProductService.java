package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.ProductDao;
import org.example.hibernateserver.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product> {
  @Autowired
  public ProductService(ProductDao productDao) {
    super(productDao);
  }
}
