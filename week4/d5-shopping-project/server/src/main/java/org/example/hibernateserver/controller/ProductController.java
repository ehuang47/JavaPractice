package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.example.hibernateserver.service.ProductService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractRestController<ProductService, Product, ProductDto, ProductQueryDto> {

  public ProductController(ProductService productService) {
    super(productService);
  }
}
