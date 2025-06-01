package org.example.hibernateserver.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.hibernateserver.dto.common.CreateDto;
import org.example.hibernateserver.dto.orderitem.OrderItemCreateDto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderCreateDto implements CreateDto {
  @NotEmpty
  private List<OrderItemCreateDto> orderItems;
}
