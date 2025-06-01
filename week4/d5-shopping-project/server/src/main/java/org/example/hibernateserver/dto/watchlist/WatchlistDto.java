package org.example.hibernateserver.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.hibernateserver.dto.common.IdentifiableDto;

@Data
@AllArgsConstructor
@Builder
public class WatchlistDto implements IdentifiableDto {
  private Long id;
  private Long userId;
  private Long productId;
}
