package com.zjq.domain;

import lombok.Data;

/**
 * <p>角色</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
@Data
public class Role {

    private int id;
    private String roleName;
    private String roleDesc;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
