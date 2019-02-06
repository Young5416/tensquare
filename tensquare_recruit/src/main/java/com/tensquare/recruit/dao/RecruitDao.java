package com.tensquare.recruit.dao;

import com.tensquare.recruit.pojo.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @description: 招聘数据访问接口
 * @author: Young
 * @create: 2019-02-06 17:11
 */
public interface RecruitDao
    extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {
  public List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String State);

  public List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String State);
}
