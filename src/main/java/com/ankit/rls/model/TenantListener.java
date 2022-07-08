package com.ankit.rls.model;

import javax.persistence.PrePersist;

public class TenantListener {

  @PrePersist
  public void setRegulator(final TenantAware entity) {
    final Integer tenantId = TenantContext.getTenantId();
    entity.setTenantId(tenantId);
  }

}
