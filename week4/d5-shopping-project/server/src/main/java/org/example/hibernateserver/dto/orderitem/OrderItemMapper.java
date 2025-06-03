package org.example.hibernateserver.dto.orderitem;

import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper implements EntityMapper<OrderItem, OrderItemDto, OrderItemCreateDto> {
  @Override
  public OrderItemDto toDto(OrderItem entity) {
    return OrderItemDto.builder()
      .id(entity.getId())
      .purchasedPrice(entity.getPurchasedPrice())
      .quantity(entity.getQuantity())
      .wholesalePrice(entity.getWholesalePrice())
      .orderId(entity.getOrderId())
      .productId(entity.getProductId())
      .build();
  }

  @Override
  public OrderItem toEntity(OrderItemCreateDto dto) {
    return OrderItem.builder()
      .purchasedPrice(dto.getPurchasedPrice())
      .quantity(dto.getQuantity())
      .wholesalePrice(dto.getWholesalePrice())
      .orderId(dto.getOrderId())
      .productId(dto.getProductId())
      .build();
  }
}
