package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 吐槽controller
 * @author: Young
 * @create: 2019-02-08 18:27
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

  @Autowired private SpitService spitService;

  @Autowired private RedisTemplate redisTemplate;

  /**
   * @Description: 查询全部数据 @Param: []
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(method = RequestMethod.GET)
  public Result findAll() {
    return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
  }

  /**
   * @Description: 根据主键查询 @Param: [id]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public Result findById(@PathVariable String id) {
    return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
  }

  /**
   * @Description: 添加 @Param: [spit]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(method = RequestMethod.POST)
  public Result add(@RequestBody Spit spit) {
    spitService.add(spit);
    return new Result(true, StatusCode.OK, "添加成功");
  }

  /**
   * @Description: 更新 @Param: [spit]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Result update(@PathVariable String id, @RequestBody Spit spit) {
    spit.set_id(id);
    spitService.add(spit);
    return new Result(true, StatusCode.OK, "修改成功");
  }

  /**
   * @Description: 根据id删除 @Param: [id]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Result deleteById(@PathVariable String id) {
    spitService.deleteById(id);
    return new Result(true, StatusCode.OK, "删除成功");
  }

  /**
   * @Description: 根据上级id查询 @Param: [parentid, page, pageSize]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(value = "/comment/{parentid}/{page}/{pageSize}", method = RequestMethod.GET)
  public Result findByParentId(
      @PathVariable String parentid, @PathVariable int page, @PathVariable int pageSize) {

    Page<Spit> byParentId = spitService.findByParentId(parentid, page, pageSize);
    return new Result(
        true,
        StatusCode.OK,
        "查询成功",
        new PageResult<Spit>(byParentId.getTotalElements(), byParentId.getContent()));
  }

  /**
   * @Description: 点赞 @Param: [id]
   *
   * @return: entity.Result @Author: Young @Date: 2019/2/8
   */
  @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
  public Result updateThumbup(@PathVariable String spitId) {

    String userId = "111";
    if (redisTemplate.opsForValue().get("thumbup_" + userId) != null) {
      return new Result(false, StatusCode.REPERROR, "不能重复点赞");
    }
    spitService.updateThumbup(spitId);
    redisTemplate.opsForValue().set("thumbup_" + userId, 1);
    return new Result(true, StatusCode.OK, "点赞成功");
  }
}
