package com.ankit.rls.model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.ConnectionProxy;
import org.springframework.jdbc.datasource.DelegatingDataSource;
import org.springframework.lang.Nullable;

@Slf4j
public class TenantAwareDataSource extends DelegatingDataSource {

  public TenantAwareDataSource(final DataSource targetDataSource) {
    super(targetDataSource);
  }

  @Override
  public Connection getConnection() throws SQLException {
    final Connection connection = getTargetDataSource().getConnection();
    setTenantId(connection);
    return getRegulatorAwareConnectionProxy(connection);
  }

  @Override
  public Connection getConnection(final String username, final String password) throws SQLException {
    final Connection connection = getTargetDataSource().getConnection(username, password);
    setTenantId(connection);
    return getRegulatorAwareConnectionProxy(connection);
  }

  // Connection Proxy that intercepts close() to reset the regulator_id
  protected Connection getRegulatorAwareConnectionProxy(final Connection connection) {
    return (Connection) Proxy.newProxyInstance(
        ConnectionProxy.class.getClassLoader(),
        new Class[]{ConnectionProxy.class},
        new TenantAwareInvocationHandler(connection));
  }

  // Connection Proxy invocation handler that intercepts close() to reset the regulator_id
  private class TenantAwareInvocationHandler implements InvocationHandler {

    private final Connection target;

    public TenantAwareInvocationHandler(final Connection target) {
      this.target = target;
    }

    @Override
    @Nullable
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
      switch (method.getName()) {
        case "equals":
          return proxy == args[0];
        case "hashCode":
          return System.identityHashCode(proxy);
        case "toString":
          return "Regulator-aware proxy for target Connection [" + this.target.toString() + "]";
        case "unwrap":
          if (((Class) args[0]).isInstance(proxy)) {
            return proxy;
          } else {
            return method.invoke(target, args);
          }
        case "isWrapperFor":
          if (((Class) args[0]).isInstance(proxy)) {
            return true;
          } else {
            return method.invoke(target, args);
          }
        case "getTargetConnection":
          return target;
        default:
          if (method.getName().equals("close")) {
            clearTenantId(target);
          }
          return method.invoke(target, args);
      }
    }
  }

  private void setTenantId(final Connection connection) throws SQLException {
    final Integer tenantId = TenantContext.getTenantId();
    if (tenantId == null) {
      return;
    }
    try (final PreparedStatement tenantIdSetQuery = connection.prepareStatement("SELECT set_config('app.tenant_id', ?, false)")) {
      tenantIdSetQuery.setString(1, tenantId.toString());
      tenantIdSetQuery.executeQuery();
    }
  }

  private void clearTenantId(final Connection connection) throws SQLException {
    if (TenantContext.getTenantId() == null) {
      return;
    }
    try (final Statement sql = connection.createStatement()) {
      sql.execute("RESET app.tenant_id");
    }
  }

}
