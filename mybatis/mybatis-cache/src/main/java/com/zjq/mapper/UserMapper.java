package com.zjq.mapper;

import com.zjq.domain.User;

import java.util.List;

/**
 * <p>用户mapper</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
public interface UserMapper {

    /**
     * 通过条件查找用户
     * @param user 查询条件
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 通过id集合查找用户
     * @param ids id集合
     * @return
     */
    List<User> findByIds(List<Integer> ids);

    /**
     * 通过id查找用户
     * @param id
     * @return
     */
    User selectUserByUserId(Integer id);


    /**
     * 通过id更新用户
     * @param user 用户实体
     * @return
     */
    int updateById(User user);


}
