package com.ankit.rls.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfo {
  private Integer id;
  private String name;
  private String email;
}
