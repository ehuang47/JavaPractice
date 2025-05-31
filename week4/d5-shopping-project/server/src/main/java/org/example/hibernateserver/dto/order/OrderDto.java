package org.example.hibernateserver.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.domain.enums.OrderStatus;
import org.example.hibernateserver.dto.common.IdentifiableDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto implements IdentifiableDto {
  private Long id;
  private OrderStatus status;
  private LocalDateTime datePlaced;
  private Long userId;
}