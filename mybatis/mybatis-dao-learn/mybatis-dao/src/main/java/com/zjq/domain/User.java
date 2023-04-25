package com.zjq.domain;

import lombok.Data;

/**
 * 用户实体
 * @author zjq
 */
@Data
public class User {

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
