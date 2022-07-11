package com.ankit.rls.interceptor;

import com.ankit.rls.exception.ErrorResponse;
import com.ankit.rls.datasource.TenantContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TenantInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    if (request.getHeader("x-tenant-id") == null) {
      response.getWriter().write(new ErrorResponse("Header x-tenant-id is missing").toString());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType("application/json");
      return false;
    }
    try {
      Integer tenantId = Integer.valueOf(request.getHeader("x-tenant-id"));
      TenantContext.setTenantId(tenantId);
      return true;
    } catch (NumberFormatException e) {
      response.getWriter().write(new ErrorResponse("Header x-tenant-id value is incorrect").toString());
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.setContentType("application/json");
      return false;
    }

  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    TenantContext.clear();
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    //NOOP
  }
}
