package com.zjq.entity;

import lombok.Data;

/**
 * 用户实体
 * @author zjq
 * @date 2022/3/14
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;
}
