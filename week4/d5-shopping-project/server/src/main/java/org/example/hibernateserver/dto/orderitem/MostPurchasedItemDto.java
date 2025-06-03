package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MostPurchasedItemDto {
  private Long productId;
  private Long totalQuantity;
}
