package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.product.ProductCreateDto;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.example.hibernateserver.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractRestController<ProductService, Product, ProductDto, ProductQueryDto, ProductCreateDto> {

  public ProductController(ProductService productService) {
    super(productService);
  }
}
