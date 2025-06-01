package org.example.hibernateserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column(name="purchased_price", nullable = false)
  private Double purchasedPrice;

  @Column(name="quantity", nullable = false)
  private Integer quantity;

  @Column(name="wholesale_price", nullable = false)
  private Double wholesalePrice;

  @ManyToOne
  @JoinColumn(name="order_id", nullable = false)
  private Order order;

  @Column(name="product_id", nullable = false)
  private Long productId;
}
