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
import javax.persistence.Table;

@Entity
@Table(name = "watchlist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Watchlist {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column(name="user_id", nullable = false, updatable = false)
  private Long userId;

  @Column(name="product_id", nullable = false, updatable = false)
  private Long productId;
}
