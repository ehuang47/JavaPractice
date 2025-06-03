package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.IdentifiableDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDto implements IdentifiableDto {
  private Long id;
  private Double purchasedPrice;
  private Integer quantity;
  private Double wholesalePrice;
  private Long orderId;
  private Long productId;
}
