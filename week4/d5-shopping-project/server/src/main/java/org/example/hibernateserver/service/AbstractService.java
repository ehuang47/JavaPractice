package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;
import org.example.hibernateserver.dto.common.CreateDto;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.common.AbstractQueryDto;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractService<E,D extends IdentifiableDto,Q extends AbstractQueryDto, C extends CreateDto> {
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
  public void save(C dto) {
    Long id = abstractDao.save(entityMapper.toEntity(buildDto(dto)));
    postSave(dto, id);
  }
  @Transactional
  public void save(C dto, Long userId) {
    Long id = abstractDao.save(entityMapper.toEntity(buildDto(dto, userId)));
    postSave(dto, id);
  }

  protected D buildDto(C dto) {return null;}
  protected D buildDto(C dto, Long userId) {return null;}

  protected void postSave(C dto, Long entityId) {}

  @Transactional
  public void update(D dto) {
    E entity = abstractDao.load(dto.getId()).orElseThrow(() -> new EntityNotFoundException(dto.getId() + " not found"));
    entityMapper.updateEntityFromDto(entity,dto);
  }
}
