package com.zjq.domain;

import lombok.Data;

@Data
public class Account {

    private String name;
    private double money;


    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
