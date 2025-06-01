package org.example.hibernateserver.dto.watchlist;

import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper implements EntityMapper<Watchlist, WatchlistDto> {

  @Override
  public WatchlistDto toDto(Watchlist entity) {
    return WatchlistDto.builder()
      .id(entity.getId())
      .userId(entity.getUserId())
      .productId(entity.getProductId())
      .build();
  }

  @Override
  public Watchlist toEntity(WatchlistDto dto) {
    return Watchlist.builder()
      .id(dto.getId())
      .userId(dto.getUserId())
      .productId(dto.getProductId())
      .build();
  }
}
