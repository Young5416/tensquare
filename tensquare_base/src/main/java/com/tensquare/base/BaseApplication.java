package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @description: tensquare_base 启动类
 * @author: Young
 * @create: 2019-02-04 20:34
 **/

@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {
  public static void main(String[] args) {
      SpringApplication.run(BaseApplication.class);
  }

  @Bean
  public IdWorker idWorker(){
      return new IdWorker(1,1);
  }
}
