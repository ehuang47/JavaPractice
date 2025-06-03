package org.example.hibernateserver.service;

import org.example.hibernateserver.dao.AbstractDao;
import org.example.hibernateserver.dto.common.CreateDto;
import org.example.hibernateserver.dto.common.EntityMapper;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.common.AbstractQueryDto;
import org.example.hibernateserver.dto.common.RequestContext;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractService<E,D extends IdentifiableDto,Q extends AbstractQueryDto, C extends CreateDto> {
  private final AbstractDao<E,Q> abstractDao;
  protected final EntityMapper<E,D,C> entityMapper;

  public AbstractService(AbstractDao<E,Q> abstractDao, EntityMapper<E,D,C> entityMapper) {
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
  public void save(C dto, RequestContext ctx) {
    E entity;
    if (saveRequiresUserId()) {
      Long userId = requireUserId(ctx);
      entity = entityMapper.toEntity(dto, userId);
    } else {
      entity = entityMapper.toEntity(dto);
    }
    Long id = abstractDao.save(entity);
    postSave(dto, id, ctx);
  }

  protected void postSave(C dto, Long entityId, RequestContext ctx) {}
  protected boolean saveRequiresUserId() { return false; }

  @Transactional
  public void update(D dto) {
    E entity = abstractDao.load(dto.getId()).orElseThrow(() -> new EntityNotFoundException(dto.getId() + " not found"));
    entityMapper.updateEntityFromDto(entity,dto);
  }

  private Long requireUserId(RequestContext ctx) {
    Long userId = ctx.getUserId();
    if (userId == null) {
      throw new IllegalArgumentException("User id required.");
    }
    return userId;
  }
}
