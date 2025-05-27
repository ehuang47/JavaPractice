package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.ProductDao;
import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends AbstractService<Product, ProductDto> {
  @Autowired
  public ProductService(ProductDao productDao, ProductMapper productMapper) {
    super(productDao,productMapper);
  }
}
