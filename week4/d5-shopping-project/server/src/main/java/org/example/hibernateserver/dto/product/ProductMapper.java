package org.example.hibernateserver.dto.product;

import org.example.hibernateserver.domain.Product;

public class ProductMapper {
  public static ProductDto toDto(Product product){
    return ProductDto.builder()
      .id(product.getId())
      .description(product.getDescription())
      .name(product.getName())
      .quantity(product.getQuantity())
      .retailPrice(product.getRetailPrice())
      .wholesalePrice(product.getWholesalePrice())
      .build();
  }
}