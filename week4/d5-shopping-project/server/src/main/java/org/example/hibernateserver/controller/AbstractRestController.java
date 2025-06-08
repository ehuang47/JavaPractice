package org.example.hibernateserver.controller;

import org.example.hibernateserver.dto.common.AbstractQueryDto;
import org.example.hibernateserver.dto.common.CreateDto;
import org.example.hibernateserver.dto.common.DataResponse;
import org.example.hibernateserver.dto.common.IdentifiableDto;
import org.example.hibernateserver.dto.common.RequestContext;
import org.example.hibernateserver.service.AbstractService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstractRestController<S extends AbstractService<E,D,Q,C>,
  E,D extends IdentifiableDto,Q extends AbstractQueryDto, C extends CreateDto> {
  protected final S abstractService;
  public AbstractRestController(S abstractService) {
    this.abstractService = abstractService;
  }

  @GetMapping("/all")
  public DataResponse<List<D>> getAll(@Valid @ModelAttribute Q queryDto, BindingResult result) {
    if (!supportsGetAll()) {
      return DataResponse.failure("Get all not supported.");
    }
    if (result.hasErrors()) {
      return DataResponse.failure("Invalid query parameters.");
    }
    return DataResponse.successWithData(abstractService.getAll(queryDto));
  }

  @GetMapping("/{id}")
  public DataResponse<D> getById(@PathVariable Long id) {
    if (!supportsGetById()) {
      return DataResponse.failure("Get by id not supported.");
    }
    return DataResponse.successWithData(abstractService.findById(id));
  }

  @PostMapping()
  public DataResponse<?> create(@Valid @RequestBody C dto, BindingResult result) {
    if (!supportsCreate()) {
      return DataResponse.failure("Create not supported.");
    }
    if (result.hasErrors()) {
      return DataResponse.failure("Invalid data.");
    }
    abstractService.save(dto, RequestContext.forUser(1L));
    return DataResponse.successWithMessage("Successfully created.");
  }
  @PatchMapping()
  public DataResponse<?> update(@Valid @RequestBody D dto, BindingResult result) {
    if (!supportsUpdate()) {
      return DataResponse.failure("Update not supported.");
    }
    if (result.hasErrors()) {
      return DataResponse.failure("Invalid data.");
    }
    abstractService.update(dto);
    return DataResponse.successWithMessage("Successfully updated.");
  }

  @DeleteMapping("/{id}")
  public DataResponse<?> delete(@PathVariable Long id) {
    if (!supportsDelete()) {
      return DataResponse.failure("Delete not supported.");
    }
    abstractService.delete(id);
    return DataResponse.successWithMessage("Successfully deleted.");
  }

  protected boolean supportsGetAll() { return true; }
  protected boolean supportsGetById() { return true; }
  protected boolean supportsCreate() { return true; }
  protected boolean supportsUpdate() { return true; }
  protected boolean supportsDelete() { return false; }
}
