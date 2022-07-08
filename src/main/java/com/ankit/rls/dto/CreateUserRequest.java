package com.ankit.rls.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserRequest {
  @NotBlank
  private String name;
  @NotBlank
  private String email;
}
