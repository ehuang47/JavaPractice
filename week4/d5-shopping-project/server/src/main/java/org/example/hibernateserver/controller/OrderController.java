package org.example.hibernateserver.controller;

import org.example.hibernateserver.domain.Order;
import org.example.hibernateserver.dto.order.OrderCreateDto;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.example.hibernateserver.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController extends AbstractRestController<OrderService, Order, OrderDto, OrderQueryDto, OrderCreateDto> {
  public OrderController(OrderService orderService) {
    super(orderService);
  }
}
