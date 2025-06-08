package org.example.hibernateserver.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.product.ProductDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProductDto {
  private Long productId;
  private String description;
  private String name;
  private Integer quantity;
  private Double retailPrice;
  private Double wholesalePrice;
  private Integer orderItemQuantity;
}
