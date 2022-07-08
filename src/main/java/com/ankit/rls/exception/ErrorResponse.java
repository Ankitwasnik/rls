package com.ankit.rls.exception;

import lombok.Data;

@Data
public class ErrorResponse {
  private final String message;

  @Override
  public String toString() {
    return "{\"message\":\"" + message + "\"}";
  }
}
