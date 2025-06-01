package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.QueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemQueryDto implements QueryDto {
  private String query;
  private String status;
  private String sortBy;
  private Integer pageSize = 5;
}
