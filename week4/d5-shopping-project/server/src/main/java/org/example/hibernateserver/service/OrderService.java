package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderDao;
import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.dto.order.OrderCreateDto;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderMapper;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  protected OrderDto buildDto(OrderCreateDto dto, Long userId) {
    return OrderDto.builder().userId(userId).build();
  }

  @Override
  protected void postSave(OrderCreateDto dto, Long entityId) {
    // optimization to get all products in 1 query: map to productIds, findAll filter by id in the list, map id to wholesale price, reuse it through the second loop
    dto.getOrderItems().forEach(orderItemCreateDto -> {
      orderItemCreateDto.setOrderId(entityId);
      Double wholesalePrice = productService.findById(orderItemCreateDto.getProductId()).getWholesalePrice();
      orderItemCreateDto.setWholesalePrice(wholesalePrice);
      orderItemService.save(orderItemCreateDto);
    });
  }
}
