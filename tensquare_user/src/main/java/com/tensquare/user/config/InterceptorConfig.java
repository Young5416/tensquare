package com.tensquare.user.config;

import com.tensquare.user.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @description: 权限拦截器
 * @author: Young
 * @create: 2019-02-17 14:51
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  @Autowired private JwtInterceptor jwtInterceptor;

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(jwtInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns("/**/login/**");
  }
}
