package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.common.DataResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
  @GetMapping("/all")
  public DataResponse<List<Product>> getAllProducts() {
    return new DataResponse<>(true, "here", List.of(new Product()));
  }
}
