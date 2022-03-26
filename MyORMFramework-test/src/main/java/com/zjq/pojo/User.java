package com.zjq.pojo;

import lombok.Data;

/**
 * 用户实体
 * @author zjq
 * @date 2022/3/17
 */
@Data
public class User {

    private Integer id;

    private String username;

    private String password;

    private String phone;

    private Integer age;

}
