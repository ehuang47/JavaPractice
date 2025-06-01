package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.WatchlistDao;
import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.watchlist.WatchlistDto;
import org.example.hibernateserver.dto.watchlist.WatchlistMapper;
import org.example.hibernateserver.dto.watchlist.WatchlistQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchlistService extends AbstractService<Watchlist, WatchlistDto, WatchlistQueryDto>{
  @Autowired
  public WatchlistService(WatchlistDao watchlistDao, WatchlistMapper watchlistMapper) {
    super(watchlistDao, watchlistMapper);
  }
}
