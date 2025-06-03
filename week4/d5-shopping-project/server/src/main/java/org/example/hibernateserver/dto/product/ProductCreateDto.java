package org.example.hibernateserver.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.CreateDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateDto implements CreateDto {
  private String description;
  private String name;
  private Integer quantity;
  private Double retailPrice;
  private Double wholesalePrice;
}
