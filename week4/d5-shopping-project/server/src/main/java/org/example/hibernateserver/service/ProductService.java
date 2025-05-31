package org.example.hibernateserver.service;

import javassist.NotFoundException;
import org.example.hibernateserver.dao.ProductDao;
import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductMapper;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService extends AbstractService<Product, ProductDto, ProductQueryDto> {
  @Autowired
  public ProductService(ProductDao productDao, ProductMapper productMapper) {
    super(productDao,productMapper);
  }

  @Transactional
  public void updateProduct(ProductDto productDto) {
    final Product product = super.load(productDto.getId());
    entityMapper.updateEntityFromDto(product, productDto);
  }
}
