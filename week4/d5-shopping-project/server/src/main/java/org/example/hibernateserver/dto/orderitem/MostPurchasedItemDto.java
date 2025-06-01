package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MostPurchasedItemDto {
  private Long productId;
  private Long totalQuantity;
}
