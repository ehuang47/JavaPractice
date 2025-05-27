package org.example.hibernateserver.dto.common;

import java.util.List;

public interface EntityMapper<E, D> {
  D toDto(E entity);
  List<D> toDtoList(List<E> entities);
}
