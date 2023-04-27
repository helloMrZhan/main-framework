package com.zjq.domain;

import lombok.Data;

import java.util.Date;

/**
 * <p>用户</p>
 *
 * @Author zjq
 * @Date 2021/8/2
 */
@Data
public class User {

    private int id;
    private String username;
    private String password;
    private Date birthday;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
