package org.example.hibernateserver.dto.common;

import lombok.Data;
@Data
public abstract class AbstractQueryDto {
  private String id;
  private String sortBy;
  private Integer page = 1;
  private Integer pageSize = 5;
}
