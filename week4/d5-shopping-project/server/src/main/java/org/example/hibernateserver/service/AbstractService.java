package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;
import org.example.hibernateserver.dto.common.CreateDto;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.common.AbstractQueryDto;
import org.example.hibernateserver.dto.common.RequestContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public abstract class AbstractService<E,D extends IdentifiableDto,Q extends AbstractQueryDto, C extends CreateDto> {
  private final AbstractDao<E,Q> abstractDao;
  protected final EntityMapper<E,D,C> entityMapper;

  public AbstractService(AbstractDao<E,Q> abstractDao, EntityMapper<E,D,C> entityMapper) {
    this.abstractDao = abstractDao;
    this.entityMapper = entityMapper;
  }

  @Transactional(readOnly = true)
  public List<D> getAll(Q queryDto) {
    return entityMapper.toDtoList(abstractDao.getAll(queryDto));
  }

  @Transactional(readOnly = true)
  public D findById(Long id) {
    return entityMapper.toDto(abstractDao.findById(id));
  }

  protected void beforeSave(E entity, C dto, RequestContext ctx) {}
  protected void afterSave(C dto, Long entityId, RequestContext ctx) {}
  protected void beforeSaveAll(List<E> entities, RequestContext ctx) {}
  protected void afterSaveAll(List<E> savedEntities, List<Long> entityIds, RequestContext ctx) {}

  // Optional user ID setting (only implement if needed)
  protected void setUserIdIfRequired(E entity, RequestContext ctx) {}
  protected void setUserIdsIfRequired(List<E> entities, RequestContext ctx) {
    entities.forEach(entity -> setUserIdIfRequired(entity, ctx));
  }
  @Transactional
  public void save(C dto, RequestContext ctx) {
    E entity = entityMapper.toEntity(dto);
    setUserIdIfRequired(entity, ctx);
    beforeSave(entity, dto, ctx);
    Long id = abstractDao.save(entity);
    afterSave(dto, id, ctx);
  }

  @Transactional
  public void saveAll(List<E> entities, RequestContext ctx){
    setUserIdsIfRequired(entities, ctx);
    beforeSaveAll(entities, ctx);
    List<Long> entityIds = abstractDao.saveAll(entities);
    afterSaveAll(entities, entityIds, ctx);
  }

  @Transactional
  public void update(D dto) {
    E entity = abstractDao.load(dto.getId()).orElseThrow(() -> new EntityNotFoundException(dto.getId() + " not found"));
    entityMapper.updateEntityFromDto(entity,dto);
  }

  public E mapToEntity(C dto) {
    return entityMapper.toEntity(dto);
  }
}
