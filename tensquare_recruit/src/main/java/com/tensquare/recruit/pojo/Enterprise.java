package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 企业实体类
 * @author: Young
 * @create: 2019-02-06 17:06
 */
@Entity
@Table(name = "tb_enterprise")
@Data
public class Enterprise {

  @Id private String id; // id

  private String name; // 企业名称
  private String summary; // 企业简介
  private String address; // 企业地址
  private String labels; // 标签列表
  private String coordinate; // 坐标
  private String ishot; // 是否热门
  private String logo; // LOGO
  private String jobcount; // 职位数
  private String url; // URL
}
