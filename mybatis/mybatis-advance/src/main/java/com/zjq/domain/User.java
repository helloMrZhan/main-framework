package com.zjq.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>用户实体</p>
 *
 * @Author zjq
 * @Date 2021/8/3
 */
@Data
public class User {

    private int id;
    private String username;
    private int age;

}
