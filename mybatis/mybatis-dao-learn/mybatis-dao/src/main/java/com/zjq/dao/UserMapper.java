package com.zjq.dao;

import com.zjq.domain.User;

import java.io.IOException;
import java.util.List;

/**
 * 用户mapper
 * @author zjq
 */
public interface UserMapper {

    /**
     * 获取所有
     * @return
     * @throws IOException
     */
    List<User> findAll() throws IOException;

    /**
     * 通过id查询
     * @param id
     * @return
     */
    User findById(int id);

}
