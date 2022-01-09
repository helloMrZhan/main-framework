package com.zjq.domain;

import lombok.Data;

import java.util.List;

@Data
public class VO {

    private List<User> userList;


    @Override
    public String toString() {
        return "VO{" +
                "userList=" + userList +
                '}';
    }
}
