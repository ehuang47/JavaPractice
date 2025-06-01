package org.example.hibernateserver.dto.common;

import java.util.List;

public interface EntityMapper<E, D> {
  D toDto(E entity);
  default List<D> toDtoList(List<E> entities) {
    return entities.stream().map(this::toDto).toList();
  }
  default E toEntity(D dto) {return null;}
  default void updateEntityFromDto(E entity, D dto) {}
}
