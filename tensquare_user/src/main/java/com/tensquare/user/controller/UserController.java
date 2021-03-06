package com.tensquare.user.controller;

import com.tensquare.user.pojo.User;
import com.tensquare.user.service.UserService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import util.JwtUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Young
 * @create: 2019-02-09 20:15
 **/

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    /** 
    * @Description: 用户登录
    * @Param: [user] 
    * @return: entity.Result 
    * @Author: Young
    * @Date: 2019/2/17 
    */ 
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestBody User user){
         User loginUser = userService.login(user);
         if(loginUser == null){
             return new Result(false,StatusCode.LOGINERROR,"登录失败");
         }
        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
         Map<String,String> map = new HashMap<>();
         map.put("token",token);
         map.put("roles","user");
        return new Result(true,StatusCode.OK,"登录成功",map);
    }

    /** 
    * @Description: 发送注册验证码 
    * @Param: [mobile] 
    * @return: entity.Result 
    * @Author: Young
    * @Date: 2019/2/9 
    */ 
    @RequestMapping(value = "/sendsms/{mobile}",method = RequestMethod.POST)
    public Result sendSms(@PathVariable String mobile){
        userService.sendSms(mobile);
        return new Result(true,StatusCode.OK,"发送成功");
    }
    
    /** 
    * @Description: 注册
    * @Param: [user] 
    * @return: entity.Result 
    * @Author: Young
    * @Date: 2019/2/9 
    */
    @RequestMapping(value = "/register/{code}", method = RequestMethod.POST)
    public Result register(@RequestBody User user,@PathVariable  String code){
        //判断验证码
        String sysCode = (String)redisTemplate.opsForValue().get("smsCode_" + user.getMobile());

        if(sysCode.isEmpty()){
            throw new RuntimeException("请获取验证码");
        }
        if(!sysCode.equals(code)){
            throw new RuntimeException("验证码输入不正确");
        }
        userService.add(user);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",userService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",userService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<User> pageList = userService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<User>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",userService.findSearch(searchMap));
    }

    /**
     * 增加
     * @param user
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody User user  ){
        userService.add(user);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param user
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody User user, @PathVariable String id ){
        user.setId(id);
        userService.update(user);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除 必须有admin角色才能删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        userService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
