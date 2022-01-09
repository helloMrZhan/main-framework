package com.zjq.dao.impl;

import com.zjq.dao.UserDao;
import org.springframework.stereotype.Repository;

//<bean id="userDao" class="UserDaoImpl"></bean>
//@Component("userDao")
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    public void save() {
        System.out.println("save running...");
    }
}
