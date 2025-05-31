package org.example.hibernateserver.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.domain.enums.OrderStatus;
import org.example.hibernateserver.dto.common.QueryDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryDto implements QueryDto {
  private String status;
  private String sortBy;
}
