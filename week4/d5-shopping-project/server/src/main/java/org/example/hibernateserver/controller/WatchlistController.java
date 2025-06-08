package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Watchlist;
import org.example.hibernateserver.dto.watchlist.WatchlistCreateDto;
import org.example.hibernateserver.dto.watchlist.WatchlistDto;
import org.example.hibernateserver.dto.watchlist.WatchlistQueryDto;
import org.example.hibernateserver.service.WatchlistService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/watchlist")
public class WatchlistController extends AbstractRestController<WatchlistService, Watchlist, WatchlistDto, WatchlistQueryDto, WatchlistCreateDto>{
  public WatchlistController(WatchlistService watchlistService) {
    super(watchlistService);
  }

  @Override
  protected boolean supportsUpdate() {
    return false;
  }

  @Override
  protected boolean supportsDelete() {
    return true;
  }
}
