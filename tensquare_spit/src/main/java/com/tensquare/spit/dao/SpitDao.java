package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @description: 吐槽数据访问层
 * @author: Young
 * @create: 2019-02-08 17:50
 **/

public interface SpitDao extends MongoRepository<Spit,String> {

    /**
    * @Description: 根据上级id查询吐槽列表
    * @Param: [parentId, pageable]
    * @return: org.springframework.data.domain.Page<com.tensquare.spit.pojo.Spit>
    * @Author: Young
    * @Date: 2019/2/8
    */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}
