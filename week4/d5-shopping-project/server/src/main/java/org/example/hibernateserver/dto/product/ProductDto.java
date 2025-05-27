package org.example.hibernateserver.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
  private Integer id;
  private String description;
  private String name;
  private Integer quantity;
  private Double retailPrice;
  private Double wholesalePrice;
}
