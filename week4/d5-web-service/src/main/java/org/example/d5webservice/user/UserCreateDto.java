package org.example.d5webservice.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserCreateDto(
  @NotBlank String name,
  @NotBlank String username,
  @Email String email) {}

