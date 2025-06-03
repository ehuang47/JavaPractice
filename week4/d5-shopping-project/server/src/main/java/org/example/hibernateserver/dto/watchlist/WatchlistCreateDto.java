package org.example.hibernateserver.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.CreateDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchlistCreateDto implements CreateDto {
  private Long productId;
}
