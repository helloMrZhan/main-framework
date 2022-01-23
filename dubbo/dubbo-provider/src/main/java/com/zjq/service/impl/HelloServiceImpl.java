package com.zjq.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zjq.service.HelloService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zjq
 */
@Service(interfaceClass = HelloService.class,protocol = "dubbo") //发布服务必须使用Dubbo提供的Service注解
@Transactional
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "8083 hello " + name;
    }
}
