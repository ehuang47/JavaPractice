package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.hibernateserver.dto.common.CreateDto;

@Data
@AllArgsConstructor
@Builder
public class OrderItemCreateDto implements CreateDto {
  private Double purchasedPrice;
  private Integer quantity;
  private Double wholesalePrice;
  private Long orderId;
  private Long productId;
}
