package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.WatchlistDao;
import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.common.RequestContext;
import org.example.hibernateserver.dto.watchlist.WatchlistCreateDto;
import org.example.hibernateserver.dto.watchlist.WatchlistDto;
import org.example.hibernateserver.dto.watchlist.WatchlistMapper;
import org.example.hibernateserver.dto.watchlist.WatchlistQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService extends AbstractService<Watchlist, WatchlistDto, WatchlistQueryDto, WatchlistCreateDto>{
  @Autowired
  public WatchlistService(WatchlistDao watchlistDao, WatchlistMapper watchlistMapper) {
    super(watchlistDao, watchlistMapper);
  }

  @Override
  protected void setUserIdIfRequired(Watchlist entity, RequestContext ctx) {
    entity.setUserId(ctx.getUserId());
  }

  @Override
  protected void beforeSave(Watchlist entity, WatchlistCreateDto dto, RequestContext ctx) {
    WatchlistQueryDto queryDto = new WatchlistQueryDto();
    queryDto.setUserId(ctx.getUserId().toString());
    queryDto.setProductId(dto.getProductId().toString());
    List<Watchlist> watchlistProducts = abstractDao.getAll(queryDto);

    if (!watchlistProducts.isEmpty()) {
      throw new IllegalStateException("Product already exists in watchlist");
    }
  }
}
