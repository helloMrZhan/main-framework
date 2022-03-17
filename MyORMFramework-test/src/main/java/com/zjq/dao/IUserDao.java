package com.zjq.dao;

import com.zjq.pojo.User;

import java.util.List;

/**
 * 用户dao
 * @author zjq
 * @date 2022/3/17
 */
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     * @throws Exception
     */
    List<User> findAll() throws Exception;

    /**
     * 根据条件进行用户查询
     * @param user
     * @return
     * @throws Exception
     */
    User findByCondition(User user) throws Exception;

}
