package com.zjq.factory;

import com.zjq.dao.UserDao;
import com.zjq.dao.impl.UserDaoImpl;

/**
 * 普通工厂
 * @author zjq
 */
public class DynamicFactory {

    public UserDao getUserDao(){
        return new UserDaoImpl();
    }

}
