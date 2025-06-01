package org.example.hibernateserver.controller;

import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.dto.orderitem.OrderItemQueryDto;
import org.example.hibernateserver.dto.orderitem.MostPurchasedItemDto;
import org.example.hibernateserver.dto.orderitem.RecentlyPurchasedItemDto;
import org.example.hibernateserver.service.OrderItemService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {
  private final OrderItemService orderItemService;
  public OrderItemController(OrderItemService orderItemService) {
    this.orderItemService = orderItemService;
  }

  @GetMapping("/most-purchased")
  public DataResponse<List<MostPurchasedItemDto>> findMostPurchasedItems(@Valid @ModelAttribute
  OrderItemQueryDto orderItemQueryDto, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to get most purchased order items.");
    }
    return DataResponse.successWithData(orderItemService.findMostPurchasedItems(orderItemQueryDto));
  }

  @GetMapping("/recent-purchased")
  public DataResponse<List<RecentlyPurchasedItemDto>> findRecentlyPurchasedItems(@Valid @ModelAttribute
                                                                         OrderItemQueryDto orderItemQueryDto, BindingResult result) {
    if (result.hasErrors()) {
      return DataResponse.failure("Unable to get recently purchased order items.");
    }
    return DataResponse.successWithData(orderItemService.findRecentlyPurchasedItems(orderItemQueryDto));
  }
}
