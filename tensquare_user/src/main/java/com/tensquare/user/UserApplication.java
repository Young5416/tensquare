package com.tensquare.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @description:
 * @author: Young
 * @create: 2019-02-09 20:15
 */
@SpringBootApplication
public class UserApplication {
  public static void main(String[] args) {
    SpringApplication.run(UserApplication.class);
  }

  @Bean
  public IdWorker idWorker() {
    return new IdWorker(1, 1);
  }
}
