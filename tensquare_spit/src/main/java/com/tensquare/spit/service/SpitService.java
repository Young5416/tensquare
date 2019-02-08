package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @description: 吐槽业务逻辑层
 * @author: Young
 * @create: 2019-02-08 17:51
 */
@Service
public class SpitService {

  @Autowired private SpitDao spitDao;

  @Autowired private IdWorker idWorker;

  @Autowired private MongoTemplate mongoTemplate;

  /**
   * @Description: 查询全部记录 @Param: []
   *
   * @return: java.util.List<com.tensquare.spit.pojo.Spit> @Author: Young @Date: 2019/2/8
   */
  public List<Spit> findAll() {
    return spitDao.findAll();
  }

  /**
   * @Description: 根据主键查询记录 @Param: [id]
   *
   * @return: com.tensquare.spit.pojo.Spit @Author: Young @Date: 2019/2/8
   */
  public Spit findById(String id) {
    Spit spit = spitDao.findById(id).get();
    return spit;
  }

  /**
   * @Description: 添加 @Param: [spit]
   *
   * @return: void @Author: Young @Date: 2019/2/8
   */
  public void add(Spit spit) {
    spit.set_id(idWorker.nextId() + ""); // 主键值
    spitDao.save(spit);
  }

  /**
   * @Description: 更新 @Param: [spit]
   *
   * @return: void @Author: Young @Date: 2019/2/8
   */
  public void update(Spit spit) {
    spitDao.save(spit);
  }

  /**
   * @Description: 删除 @Param: [id]
   *
   * @return: void @Author: Young @Date: 2019/2/8
   */
  public void deleteById(String id) {
    spitDao.deleteById(id);
  }

  /**
   * @Description: 根据上级id查询吐槽列表 @Param: [parentId, page, pageSize]
   *
   * @return: org.springframework.data.domain.Page<com.tensquare.spit.pojo.Spit> @Author:
   *     Young @Date: 2019/2/8
   */
  public Page<Spit> findByParentId(String parentid, int page, int pageSize) {
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
    return spitDao.findByParentid(parentid, pageRequest);
  }

  public void updateThumbup(String id) {

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(id));
    Update update = new Update();
    update.inc("thumbup", 1);
    mongoTemplate.updateFirst(query, update, "spit");
  }
}
