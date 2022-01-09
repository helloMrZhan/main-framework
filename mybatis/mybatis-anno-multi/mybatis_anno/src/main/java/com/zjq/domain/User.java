package com.zjq.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>用户</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private Date birthday;

    /**
     * 当前用户具备哪些角色
     */
    private List<Role> roleList;

    /**
     * 描述的是当前用户具有的订单
     */
    private List<Order> orderList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", roleList=" + roleList +
                ", orderList=" + orderList +
                '}';
    }
}
