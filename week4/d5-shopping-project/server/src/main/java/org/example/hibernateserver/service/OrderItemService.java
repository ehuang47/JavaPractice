package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderItemDao;
import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.orderitem.OrderItemDto;
import org.example.hibernateserver.dto.orderitem.OrderItemMapper;
import org.example.hibernateserver.dto.orderitem.OrderItemQueryDto;
import org.example.hibernateserver.dto.orderitem.MostPurchasedItemDto;
import org.example.hibernateserver.dto.orderitem.RecentlyPurchasedItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderItemService extends AbstractService<OrderItem, OrderItemDto, OrderItemQueryDto>{
  private final OrderItemDao orderItemDao;
  @Autowired
  public OrderItemService(OrderItemDao orderItemDao, OrderItemMapper orderItemMapper) {
    super(orderItemDao, orderItemMapper);
    this.orderItemDao = orderItemDao;
  }

  @Transactional
  public List<MostPurchasedItemDto> findMostPurchasedItems(OrderItemQueryDto orderItemQueryDto) {
    return orderItemDao.findMostPurchased(orderItemQueryDto);
  }

  @Transactional
  public List<RecentlyPurchasedItemDto> findRecentlyPurchasedItems(OrderItemQueryDto orderItemQueryDto) {
    return orderItemDao.findRecentPurchases(orderItemQueryDto);
  }
}
