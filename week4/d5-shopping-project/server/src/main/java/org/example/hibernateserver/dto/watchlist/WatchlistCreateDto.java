package org.example.hibernateserver.dto.watchlist;

import lombok.Builder;
import lombok.Data;
import org.example.hibernateserver.dto.common.CreateDto;

@Data
@Builder
public class WatchlistCreateDto implements CreateDto {
  private Long productId;
}
