package com.zjq.factory;

import com.zjq.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * FactoryBean⾃定义Bean的创建过程
 * @author dell
 */
public class UserFactoryBean implements FactoryBean<User> {
    /**
     * 用户姓名,地址
     */
    private String userInfo;
    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }
    @Override
    public User getObject() throws Exception {
        // 模拟创建复杂对象User
        User user = new User();
        String[] strings = userInfo.split(",");
        user.setName(strings[0]);
        user.setAddr(strings[1]);
        return user;
    }
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
    @Override
    public boolean isSingleton() {
        return true;
    }
}
