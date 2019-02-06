package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: 企业数据访问接口
 * @author: Young
 * @create: 2019-02-04 20:41
 */
public interface EnterpriseDao
    extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
  public List<Enterprise> findByIshot(String isHot);
}
