package com.ankit.rls.config;

import com.ankit.rls.interceptor.TenantInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

  private final TenantInterceptor tenantInterceptor;

  @Override
  public void addInterceptors(final InterceptorRegistry registry) {
    registry.addInterceptor(tenantInterceptor);
  }

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        .allowedOrigins("*")
        .allowedHeaders("*");
  }

}

