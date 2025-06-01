package org.example.hibernateserver.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProductQueryDto extends AbstractQueryDto {
  private Boolean inStockOnly = false;
//  private String category;
//
//  private Double minPrice;
//
//  private Double maxPrice;
//
//  private String search;
}
