package com.zjq.domain;

import lombok.Data;

@Data
public class User {

    private String username;
    private int age;


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
