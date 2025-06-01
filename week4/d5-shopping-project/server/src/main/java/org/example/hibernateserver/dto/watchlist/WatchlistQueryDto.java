package org.example.hibernateserver.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class WatchlistQueryDto extends AbstractQueryDto {
  private Boolean inStockOnly = false;
}
