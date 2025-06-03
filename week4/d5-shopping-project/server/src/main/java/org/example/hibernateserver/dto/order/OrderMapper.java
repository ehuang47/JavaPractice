package org.example.hibernateserver.dto.order;

import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements EntityMapper<Order, OrderDto, OrderCreateDto> {
  @Override
  public OrderDto toDto(Order order){
    return OrderDto.builder()
      .id(order.getId())
      .status(order.getStatus())
      .datePlaced(order.getDatePlaced())
      .userId(order.getUserId())
      .build();
  }

  @Override
  public Order toEntity(OrderCreateDto dto, Long userId) {
    return Order.builder()
      .userId(userId)
      .build();
  }

  @Override
  public void updateEntityFromDto(Order entity, OrderDto dto) {
    entity.setStatus(dto.getStatus());
  }
}