package org.example.hibernateserver.controller;

import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.dto.order.OrderDto;
import org.example.hibernateserver.dto.order.OrderQueryDto;
import org.example.hibernateserver.service.OrderService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
  private final OrderService orderService;

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping("/all")
  public DataResponse<List<OrderDto>> findAllProducts(@Valid @ModelAttribute OrderQueryDto orderQueryDto, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to get orders.");
    }
    return DataResponse.successWithData(orderService.getAll(orderQueryDto));
  }

  @GetMapping("/{id}")
  public DataResponse<OrderDto> findProductById(@PathVariable Long id) {
    return DataResponse.successWithData(orderService.findById(id));
  }

  @PostMapping()
  public DataResponse<?> addProduct(@Valid @RequestBody OrderDto order, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to add order.");
    }
    orderService.add(order);
    return DataResponse.successWithMessage("Successfully added new order.");
  }

  @PatchMapping()
  public DataResponse<?> updateProduct(@Valid @RequestBody OrderDto order, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to update order.");
    }
    orderService.update(order);
    return DataResponse.successWithMessage("Successfully updated order.");
  }
}
