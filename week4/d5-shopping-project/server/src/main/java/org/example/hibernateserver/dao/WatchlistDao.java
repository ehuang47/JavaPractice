package org.example.hibernateserver.dao;

import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.watchlist.WatchlistQueryDto;
import org.springframework.stereotype.Repository;

@Repository
public class WatchlistDao extends AbstractDao<Watchlist, WatchlistQueryDto> {
  public WatchlistDao() {
    settClass(Watchlist.class);
  }
}
