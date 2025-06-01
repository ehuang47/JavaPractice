package org.example.hibernateserver.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderQueryDto extends AbstractQueryDto {
  private String status;
}
