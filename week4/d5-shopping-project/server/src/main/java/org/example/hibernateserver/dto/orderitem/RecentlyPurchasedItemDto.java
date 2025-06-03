package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentlyPurchasedItemDto {
  private Long productId;
  private Integer quantity;
  private LocalDateTime datePurchased;
}
