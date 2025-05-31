package org.example.hibernateserver.controller;

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
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/all")
  public DataResponse<List<ProductDto>> findAllProducts(@Valid @ModelAttribute ProductQueryDto productQueryDto, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to get products.");
    }
    return DataResponse.successWithData(productService.getAll(productQueryDto));
  }

  @GetMapping("/{id}")
  public DataResponse<ProductDto> findProductById(@PathVariable Long id) {
    return DataResponse.successWithData(productService.findById(id));
  }

  @PostMapping()
  public DataResponse<?> addProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to add product.");
    }
    productService.add(product);
    return DataResponse.successWithMessage("Successfully added new product.");
  }

  @PatchMapping()
  public DataResponse<?> updateProduct(@Valid @RequestBody ProductDto product, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to update product.");
    }
    productService.update(product);
    return DataResponse.successWithMessage("Successfully updated product.");
  }
}
