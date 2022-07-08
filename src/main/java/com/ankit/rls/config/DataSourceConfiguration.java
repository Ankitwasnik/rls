package com.ankit.rls.config;

import com.ankit.rls.model.TenantAwareDataSource;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfiguration {

  @Bean
  @ConfigurationProperties("flyway.datasource")
  public DataSourceProperties flywayDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @FlywayDataSource
  @ConfigurationProperties("flyway.datasource.hikari")
  public DataSource flywayDataSource() {
    final HikariDataSource dataSource = flywayDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("flywayDataSource");
    dataSource.setMaximumPoolSize(2);
    dataSource.setMinimumIdle(1);
    return dataSource;
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.datasource")
  public DataSourceProperties applicationDataSourceProperties() {
    return new DataSourceProperties();
  }

  @Bean
  @Primary
  @ConfigurationProperties("application.datasource.hikari")
  public DataSource applicationDataSource() {
    final HikariDataSource dataSource = applicationDataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
    dataSource.setPoolName("applicationDataSource");
    return new TenantAwareDataSource(dataSource);
  }

}
