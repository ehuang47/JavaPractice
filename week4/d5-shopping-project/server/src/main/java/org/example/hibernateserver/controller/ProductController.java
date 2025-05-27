package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductMapper;
import org.example.hibernateserver.dto.product.ProductQueryRequest;
import org.example.hibernateserver.service.ProductService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  public DataResponse<List<ProductDto>> findAllProducts(@Valid @ModelAttribute ProductQueryRequest productQueryRequest, BindingResult result) {
    System.out.println(productQueryRequest);
    System.out.println(result);
    return new DataResponse<>(true, "here", productService.getAll());
  }

  @GetMapping("/{id}")
  public DataResponse<ProductDto> findProductById(@PathVariable int id) {
    return new DataResponse<>(true, "here", productService.findById(id));
  }
}
