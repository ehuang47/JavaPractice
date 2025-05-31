package org.example.hibernateserver.dto.order;

import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper implements EntityMapper<Order, OrderDto> {
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
  public List<OrderDto> toDtoList(List<Order> orders) {
    return orders.stream().map(this::toDto).toList();
  }

  @Override
  public Order toEntity(OrderDto order) {
    return Order.builder()
      .id(order.getId())
      .status(order.getStatus())
      .datePlaced(order.getDatePlaced())
      .userId(order.getUserId())
      .build();
  }

  @Override
  public void updateEntityFromDto(Order entity, OrderDto dto) {
    entity.setStatus(dto.getStatus());
  }
}