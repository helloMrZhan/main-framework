package com.zjq.mapper;

import com.zjq.domain.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll();

    List<User> findUserAndRoleAll();

}
