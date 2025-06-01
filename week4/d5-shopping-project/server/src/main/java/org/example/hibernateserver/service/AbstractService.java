package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractService<E,D extends IdentifiableDto,Q extends AbstractQueryDto> {
  private final AbstractDao<E,Q> abstractDao;
  protected final EntityMapper<E,D> entityMapper;

  public AbstractService(AbstractDao<E,Q> abstractDao, EntityMapper<E,D> entityMapper) {
    this.abstractDao = abstractDao;
    this.entityMapper = entityMapper;
  }

  @Transactional
  public List<D> getAll(Q queryDto) {
    return entityMapper.toDtoList(abstractDao.getAll(queryDto));
  }

  @Transactional
  public D findById(Long id) {
    return entityMapper.toDto(abstractDao.findById(id));
  }

  @Transactional
  public void add(D dto) {
    abstractDao.add(entityMapper.toEntity(dto));
  }

  @Transactional
  public void update(D dto) {
    E entity = abstractDao.load(dto.getId()).orElseThrow(() -> new EntityNotFoundException(dto.getId() + " not found"));
    entityMapper.updateEntityFromDto(entity,dto);
  }
}
