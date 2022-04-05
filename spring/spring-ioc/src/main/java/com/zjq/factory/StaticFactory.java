package com.zjq.factory;

import com.zjq.dao.UserDao;
import com.zjq.dao.impl.UserDaoImpl;

/**
 * 静态工厂
 * @author zjq
 */
public class StaticFactory {

    public static UserDao getUserDao(){
        return new UserDaoImpl();
    }


}
