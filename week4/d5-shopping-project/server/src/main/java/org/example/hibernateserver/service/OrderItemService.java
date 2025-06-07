package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.OrderItemDao;
import org.example.hibernateserver.domain.OrderItem;
import org.example.hibernateserver.dto.common.RequestContext;
import org.example.hibernateserver.dto.orderitem.MostPurchasedItemDto;
import org.example.hibernateserver.dto.orderitem.OrderItemCreateDto;
import org.example.hibernateserver.dto.orderitem.OrderItemDto;
import org.example.hibernateserver.dto.orderitem.OrderItemMapper;
import org.example.hibernateserver.dto.orderitem.OrderItemQueryDto;
import org.example.hibernateserver.dto.orderitem.RecentlyPurchasedItemDto;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderItemService extends AbstractService<OrderItem, OrderItemDto, OrderItemQueryDto, OrderItemCreateDto> {
  private final OrderItemDao orderItemDao;
  private final ProductService productService;

  @Autowired
  public OrderItemService(OrderItemDao orderItemDao, OrderItemMapper orderItemMapper, ProductService productService) {
    super(orderItemDao, orderItemMapper);
    this.orderItemDao = orderItemDao;
    this.productService = productService;
  }

  @Transactional(readOnly = true)
  public List<MostPurchasedItemDto> findMostPurchasedItems(OrderItemQueryDto orderItemQueryDto) {
    return orderItemDao.findMostPurchased(orderItemQueryDto);
  }

  @Transactional(readOnly = true)
  public List<RecentlyPurchasedItemDto> findRecentlyPurchasedItems(OrderItemQueryDto orderItemQueryDto) {
    return orderItemDao.findRecentPurchases(orderItemQueryDto);
  }

  @Override
  protected void beforeSaveAll(List<OrderItem> entities, RequestContext ctx) {
    String productIds = entities.stream()
      .map(orderItem -> String.valueOf(orderItem.getProductId()))
      .collect(Collectors.joining(","));

    ProductQueryDto productQueryDto = new ProductQueryDto();
    productQueryDto.setId(productIds);
    List<ProductDto> products = productService.getAll(productQueryDto);

    Map<Long, ProductDto> productMap = products.stream()
      .collect(Collectors.toMap(ProductDto::getId, product -> product));

    for (OrderItem orderItem : entities) {
      ProductDto product = productMap.get(orderItem.getProductId());
      if (product != null) {
        orderItem.setPurchasedPrice(product.getRetailPrice());
        orderItem.setWholesalePrice(product.getWholesalePrice());
      }
    }
  }
}
