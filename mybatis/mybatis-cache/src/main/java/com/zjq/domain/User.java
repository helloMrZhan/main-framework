package com.zjq.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>用户</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
@Data
public class User implements Serializable {

    private int id;
    private String username;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
