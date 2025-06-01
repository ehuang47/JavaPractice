package org.example.hibernateserver.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.QueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryDto implements QueryDto {
  private Boolean inStockOnly = false;
  private String sortBy;
  private Integer pageSize;

//  private String category;
//
//  private Double minPrice;
//
//  private Double maxPrice;
//
//  private String search;
}
