package com.tensquare.user.dao;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @description: 用户数据访问层
 * @author: Young
 * @create: 2019-02-09 20:14
 **/

public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {

}
