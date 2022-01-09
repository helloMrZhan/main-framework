package com.zjq.factory;

import com.zjq.dao.UserDao;
import com.zjq.dao.impl.UserDaoImpl;

public class DynamicFactory {

    public UserDao getUserDao(){
        return new UserDaoImpl();
    }

}
