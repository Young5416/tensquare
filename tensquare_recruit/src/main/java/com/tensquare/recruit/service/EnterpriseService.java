package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.EnterpriseDao;
import com.tensquare.recruit.pojo.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * @description: 企业service层
 * @author: Young
 * @create: 2019-02-06 17:23
 */
@Service
@Transactional
public class EnterpriseService {

  @Autowired private EnterpriseDao enterpriseDao;

  @Autowired private IdWorker idWorker;

  public List<Enterprise> hotList(String isHot) {
    return enterpriseDao.findByIshot(isHot);
  }
}
