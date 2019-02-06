package com.tensquare.recruit.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description: 招聘职位实体类
 * @author: Young
 * @create: 2019-02-06 16:55
 */
@Entity
@Table(name = "tb_recruit")
@Data
public class Recruit {

  @Id private String id; // id

  private String jobname; // 职位名称
  private String salary; // 薪资范围
  private String condition; // 经验要求
  private String education; // 学历要求
  private String type; // id /任职方式
  private String address; // 办公地址
  private String eid; // 企业id
  private String createtime; // 创建日期
  private String state; // 状态
  private String url; // 网址
}
