package com.zjq.mapper;

import com.zjq.domain.User;

import java.util.List;

/**
 * <p>用户dao</p>
 *
 * @Author zjq
 * @Date 2021/8/2
 */
public interface UserMapper {

    /**
     * 保存
     * @param user
     */
    void save(User user);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    User findById(int id);

    /**
     * 查找所有
     * @return
     */
    List<User> findAll();

}
