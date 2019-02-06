package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 招聘数据访问接口
 * @author: Young
 * @create: 2019-02-06 17:11
 */
public interface RecruitDao
    extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {}
