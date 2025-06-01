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

  @Override
  public List<OrderItemDto> toDtoList(List<OrderItem> entities) {
    return entities.stream().map(this::toDto).toList();
  }

  @Override
  public OrderItem toEntity(OrderItemDto dto) {
    return OrderItem.builder()
      .id(dto.getId())
      .purchasedPrice(dto.getPurchasedPrice())
      .quantity(dto.getQuantity())
      .wholesalePrice(dto.getWholesalePrice())
      .order(null) // should be fixed. ill disable adding new order item directly, so it would never call this
      .productId(dto.getProductId())
      .build();
  }

  @Override
  public void updateEntityFromDto(OrderItem entity, OrderItemDto dto) {

  }
}
