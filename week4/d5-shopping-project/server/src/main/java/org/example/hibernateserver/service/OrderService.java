package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderDao;
import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.common.RequestContext;
import org.example.hibernateserver.dto.order.OrderCreateDto;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderMapper;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService<Order, OrderDto, OrderQueryDto, OrderCreateDto> {
  private final OrderItemService orderItemService;
  @Autowired
  public OrderService(OrderDao orderDao, OrderMapper orderMapper, OrderItemService orderItemService) {
    super(orderDao, orderMapper);
    this.orderItemService = orderItemService;
  }

  @Override
  protected void afterSave(OrderCreateDto dto, Long entityId, RequestContext ctx) {
    List<OrderItem> orderItems = dto.getOrderItems().stream()
      .map(orderItemCreateDto -> {
        OrderItem orderItem = orderItemService.mapToEntity(orderItemCreateDto);
        orderItem.setOrderId(entityId);
        return orderItem;
      })
      .toList();
    orderItemService.saveAll(orderItems, ctx);
  }

  @Override
  protected void setUserIdIfRequired(Order entity, RequestContext ctx) {
    entity.setUserId(ctx.getUserId());
  }
}
