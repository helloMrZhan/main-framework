package com.zjq.factory;

import com.zjq.dao.UserDao;
import com.zjq.dao.impl.UserDaoImpl;

public class StaticFactory {

    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }

}
