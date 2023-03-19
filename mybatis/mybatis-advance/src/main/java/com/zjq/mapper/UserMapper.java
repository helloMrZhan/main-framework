package com.zjq.mapper;

import com.zjq.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>用户mapper</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
public interface UserMapper {

    /**
     * 批量插入用户
     * @param userList
     */
    void batchInsertUser(@Param("list") List<User> userList);

    /**
     * 新增单个用户
     * @param user
     */
    void insertUser(User user);


}
