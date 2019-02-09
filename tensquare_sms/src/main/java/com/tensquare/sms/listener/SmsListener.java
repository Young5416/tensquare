package com.tensquare.sms.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 短信监听类
 * @author: Young
 * @create: 2019-02-09 21:17
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

  /**
   * @Description: 发送阿里大于短信
   * @Param: [map]
   *
   * @return: void @Author: Young @Date: 2019/2/9
   */
  @RabbitHandler
  public void sendSms(Map<String, String> message) {
    System.out.println("手机号: " + message.get("mobile"));
    System.out.println("手机号: " + message.get("code"));
  }
}
