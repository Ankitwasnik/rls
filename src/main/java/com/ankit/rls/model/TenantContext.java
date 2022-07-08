package com.ankit.rls.model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TenantContext {

  private static final InheritableThreadLocal<Integer> currentTenant = new InheritableThreadLocal<>();

  private TenantContext() {
  }

  public static Integer getTenantId() {
    return currentTenant.get();
  }

  public static void setTenantId(final Integer tenantId) {
    currentTenant.set(tenantId);
  }

  public static void clear() {
    currentTenant.remove();
  }

}
