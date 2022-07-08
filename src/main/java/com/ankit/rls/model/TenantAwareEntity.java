package com.ankit.rls.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TenantAwareEntity implements TenantAware {

  @Column(name = "tenant_id", nullable = false)
  private Integer tenantId;

  public Integer getTenantId() {
    return tenantId;
  }

  public void setTenantId(Integer tenantId) {
    this.tenantId = tenantId;
  }
}