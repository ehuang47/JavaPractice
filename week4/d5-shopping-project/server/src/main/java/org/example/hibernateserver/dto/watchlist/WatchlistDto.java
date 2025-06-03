package org.example.hibernateserver.dto.watchlist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hibernateserver.dto.common.IdentifiableDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WatchlistDto implements IdentifiableDto {
  private Long id;
  private Long userId;
  private Long productId;
}
