package org.example.hibernateserver.dto.product;

import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductMapper implements EntityMapper<Product, ProductDto> {
  @Override
  public ProductDto toDto(Product product){
    return ProductDto.builder()
      .id(product.getId())
      .description(product.getDescription())
      .name(product.getName())
      .quantity(product.getQuantity())
      .retailPrice(product.getRetailPrice())
      .wholesalePrice(product.getWholesalePrice())
      .build();
  }

  @Override
  public List<ProductDto> toDtoList(List<Product> products) {
    return products.stream().map(this::toDto).toList();
  }

  @Override
  public Product toEntity(ProductDto product) {
    return Product.builder()
      .id(product.getId())
      .description(product.getDescription())
      .name(product.getName())
      .quantity(product.getQuantity())
      .retailPrice(product.getRetailPrice())
      .wholesalePrice(product.getWholesalePrice())
      .build();
  }

  @Override
  public void updateEntityFromDto(Product entity, ProductDto dto) {
    entity.setDescription(dto.getDescription());
    entity.setName(dto.getName());
    entity.setQuantity(dto.getQuantity());
    entity.setRetailPrice(dto.getRetailPrice());
    entity.setWholesalePrice(dto.getWholesalePrice());
  }
}