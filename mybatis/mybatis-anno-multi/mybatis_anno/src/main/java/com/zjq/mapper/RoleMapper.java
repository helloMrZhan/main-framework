package com.zjq.mapper;

import com.zjq.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>角色mapper</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
public interface RoleMapper {

    /**
     * 通过用户id查找角色
     * @param uid
     * @return
     */
    @Select("SELECT * FROM sys_user_role ur,sys_role r WHERE ur.roleId=r.id AND ur.userId=#{uid}")
    List<Role> findByUid(int uid);

}
