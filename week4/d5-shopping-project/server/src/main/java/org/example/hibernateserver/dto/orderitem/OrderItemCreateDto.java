package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.CreateDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemCreateDto implements CreateDto {
  private Integer quantity;
  private Long productId;
}
