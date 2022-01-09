package com.zjq.demo;

import com.zjq.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserController {

    public static void main(String[] args) {
        //ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ApplicationContext app = new FileSystemXmlApplicationContext("E:\\myworkspace\\main-framework\\spring\\spring-ioc\\src\\main\\resources\\applicationContext.xml");
        //UserService userService = (UserService) app.getBean("userService");
        UserService userService = app.getBean(UserService.class);
        userService.save();
    }

}
