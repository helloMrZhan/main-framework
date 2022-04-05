package com.zjq.service.impl;

import com.zjq.dao.UserDao;
import com.zjq.service.UserService;
import org.springframework.beans.factory.BeanFactory;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserServiceImpl() {
    }

    /*public void setUserDao(UserDao userDao) {
                this.userDao = userDao;
            }*/
    @Override
    public void save() {
        userDao.save();
    }
}
