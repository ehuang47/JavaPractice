package org.example.hibernateserver.dto.common;

import java.util.List;

public interface EntityMapper<E, D,C extends CreateDto> {
  D toDto(E entity);
  default List<D> toDtoList(List<E> entities) {
    return entities.stream().map(this::toDto).toList();
  }
  default E toEntity(C dto) {return null;}
  default void updateEntityFromDto(E entity, D dto) {}
}
