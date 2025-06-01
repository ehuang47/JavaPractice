package org.example.hibernateserver.dto.orderitem;

import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemMapper implements EntityMapper<OrderItem, OrderItemDto> {
  @Override
  public OrderItemDto toDto(OrderItem entity) {
    return OrderItemDto.builder()
      .id(entity.getId())
      .purchasedPrice(entity.getPurchasedPrice())
      .quantity(entity.getQuantity())
      .wholesalePrice(entity.getWholesalePrice())
      .orderId(entity.getOrder().getId())
      .productId(entity.getProductId())
      .build();
  }
}
