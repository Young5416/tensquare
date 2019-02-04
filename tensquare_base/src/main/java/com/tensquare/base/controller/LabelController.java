package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 标签控制层
 * @author: Young
 * @create: 2019-02-04 20:57
 **/

@RestController
@RequestMapping("/label")
@CrossOrigin
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
    * @Description:  查询所有标签
    * @Param: []
    * @return: entity.Result
    * @Author: Young
    * @Date: 2019/2/4
    */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){

        return new Result(true,StatusCode.OK,"查询成功",labelService.findAll());
    }

    /**
    * @Description: 根据id查询标签
    * @Param: [labelId]
    * @return: entity.Result
    * @Author: Young
    * @Date: 2019/2/4
    */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String labelId){

        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    /**
    * @Description: 添加标签
    * @Param: [label]
    * @return: entity.Result
    * @Author: Young
    * @Date: 2019/2/4
    */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }

    /**
    * @Description: 更新标签
    * @Param: [labelId, label]
    * @return: entity.Result
    * @Author: Young
    * @Date: 2019/2/4
    */
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"更新成功");
    }

    /** 
    * @Description: 根据id删除标签 
    * @Param: [labelId]
    * @return: entity.Result
    * @Author: Young
    * @Date: 2019/2/4 
    */ 
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
