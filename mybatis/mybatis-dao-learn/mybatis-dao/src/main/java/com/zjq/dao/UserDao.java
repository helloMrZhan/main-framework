package com.zjq.dao;

import com.zjq.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserDao {
    List<User> findAll() throws IOException;
}
