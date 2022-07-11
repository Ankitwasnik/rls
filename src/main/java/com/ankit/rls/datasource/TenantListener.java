package com.ankit.rls.datasource;

import com.ankit.rls.model.TenantAware;
import javax.persistence.PrePersist;

public class TenantListener {

  @PrePersist
  public void setTenant(final TenantAware entity) {
    final Integer tenantId = TenantContext.getTenantId();
    entity.setTenantId(tenantId);
  }

}
