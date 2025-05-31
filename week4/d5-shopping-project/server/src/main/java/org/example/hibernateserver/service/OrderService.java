package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderDao;
import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderMapper;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Order, OrderDto, OrderQueryDto> {
  @Autowired
  public OrderService(OrderDao orderDao, OrderMapper orderMapper) {
    super(orderDao, orderMapper);
  }
}
