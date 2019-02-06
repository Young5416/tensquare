package com.tensquare.recruit.service;

import com.tensquare.recruit.dao.RecruitDao;
import com.tensquare.recruit.pojo.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.List;

/**
 * @description: 招聘service层
 * @author: Young
 * @create: 2019-02-06 17:44
 **/

@Service
@Transactional
public class RecruitService {

    @Autowired
    private RecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;

    public List<Recruit> recommend(){
        return recruitDao.findTop6ByStateOrderByCreatetimeDesc("2");
    }

    public List<Recruit> newList(){
        return recruitDao.findTop6ByStateNotOrderByCreatetimeDesc("0");
    }
}
