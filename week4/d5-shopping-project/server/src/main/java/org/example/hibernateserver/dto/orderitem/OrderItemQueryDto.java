package org.example.hibernateserver.dto.orderitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderItemQueryDto extends AbstractQueryDto {
  private String query;
  private String status;
}
