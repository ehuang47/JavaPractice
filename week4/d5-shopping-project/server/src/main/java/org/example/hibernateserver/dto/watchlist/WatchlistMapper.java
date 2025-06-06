package org.example.hibernateserver.dto.watchlist;

import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class WatchlistMapper implements EntityMapper<Watchlist, WatchlistDto, WatchlistCreateDto> {

  @Override
  public WatchlistDto toDto(Watchlist entity) {
    return WatchlistDto.builder()
      .id(entity.getId())
      .userId(entity.getUserId())
      .productId(entity.getProductId())
      .build();
  }

  @Override
  public Watchlist toEntity(WatchlistCreateDto dto) {
    return Watchlist.builder()
      .productId(dto.getProductId())
      .build();
  }
}
