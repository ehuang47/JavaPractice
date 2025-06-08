package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.ProductDao;
import org.example.hibernateserver.domain.Product;
import org.example.hibernateserver.dto.product.ProductCreateDto;
import org.example.hibernateserver.dto.product.ProductDto;
import org.example.hibernateserver.dto.product.ProductMapper;
import org.example.hibernateserver.dto.product.ProductQueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService extends AbstractService<Product, ProductDto, ProductQueryDto, ProductCreateDto> {
  @Autowired
  public ProductService(ProductDao productDao, ProductMapper productMapper) {
    super(productDao,productMapper);
  }

  public ProductQueryDto getProductQuery(Map<Long, Integer> productQuantities) {
    // Get all products in a single batch
    ProductQueryDto productQuery = new ProductQueryDto();
    productQuery.setId(
      productQuantities.keySet().stream()
        .map(String::valueOf)
        .collect(Collectors.joining(","))
    );
    return productQuery;
  }

  @Transactional(readOnly = true)
  public void validateSubtractProductQuantities(Map<Long, Integer> productQuantities) {
    if (CollectionUtils.isEmpty(productQuantities)) {
      throw new IllegalArgumentException("No products provided");
    }
    List<ProductDto> products = getAll(getProductQuery(productQuantities));
    if (products.size() != productQuantities.size()) {
      throw new IllegalArgumentException("One or more products not found");
    }
    for (ProductDto product : products) {
      Integer quantity = productQuantities.get(product.getId());
      int resultingQuantity = product.getQuantity() - quantity;
      if (resultingQuantity < 0) {
        throw new IllegalStateException("Insufficient stock for product id " + product.getId());
      }
    }
  }

  /**
   * Updates product quantities based on the provided map.
   * @param productQuantities Map of product IDs to quantity adjustments
   * @param isRestocking If true, adds to inventory; if false, subtracts from inventory
   * @throws IllegalStateException if subtraction would result in negative inventory
   */
  @Transactional
  public void updateProductQuantities(Map<Long, Integer> productQuantities, boolean isRestocking) {
    if (CollectionUtils.isEmpty(productQuantities)) {
      throw new IllegalArgumentException("No products provided");
    }
    List<ProductDto> products = getAll(getProductQuery(productQuantities));
    if (products.size() != productQuantities.size()) {
      throw new IllegalArgumentException("One or more products not found");
    }
    for (ProductDto product : products) {
      Integer quantity = productQuantities.get(product.getId());
      int resultingQuantity = isRestocking
        ? product.getQuantity() + quantity
        : product.getQuantity() - quantity;
      product.setQuantity(resultingQuantity);
      update(product);
    }
  }
}
