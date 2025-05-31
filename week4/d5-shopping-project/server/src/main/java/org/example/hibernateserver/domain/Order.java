package org.example.hibernateserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hibernateserver.domain.enums.OrderStatus;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name="status", nullable = false, insertable = false)
  @Generated(GenerationTime.INSERT)
  private OrderStatus status;

  @Column(name="date_placed", nullable = false, insertable = false, updatable = false)
  @Generated(GenerationTime.INSERT)
  private LocalDateTime datePlaced;

  @Column(name="user_id", nullable = false, updatable = false)
  private Long userId;
}
