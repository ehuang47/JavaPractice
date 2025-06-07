package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderDao;
import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.common.RequestContext;
import org.example.hibernateserver.dto.order.OrderCreateDto;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderMapper;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.example.hibernateserver.dto.orderitem.OrderItemCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService extends AbstractService<Order, OrderDto, OrderQueryDto, OrderCreateDto> {
  private final OrderItemService orderItemService;
  private final ProductService productService;

  @Autowired
  public OrderService(OrderDao orderDao, OrderMapper orderMapper, OrderItemService orderItemService, ProductService productService) {
    super(orderDao, orderMapper);
    this.orderItemService = orderItemService;
    this.productService = productService;
  }

  @Override
  protected void beforeSave(Order entity, OrderCreateDto dto, RequestContext ctx) {
    productService.validateSubtractProductQuantities(getProductQuantities(dto));
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
    productService.updateProductQuantities(getProductQuantities(dto), false);
  }

  @Override
  protected void setUserIdIfRequired(Order entity, RequestContext ctx) {
    entity.setUserId(ctx.getUserId());
  }

  private Map<Long, Integer> getProductQuantities(OrderCreateDto dto) {
    // Group order items by product ID to avoid multiple updates for same product
    return dto.getOrderItems().stream()
      .collect(Collectors.groupingBy(
        OrderItemCreateDto::getProductId,
        Collectors.summingInt(OrderItemCreateDto::getQuantity)
      ));
  }
}
