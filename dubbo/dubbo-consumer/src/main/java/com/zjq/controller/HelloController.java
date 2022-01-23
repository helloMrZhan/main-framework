package com.zjq.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zjq.service.HelloService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zjq
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @Reference
    private HelloService helloService;

    @RequestMapping("/sayHello")
    @ResponseBody
    public String sayHello(String name){
        return helloService.sayHello(name);
    }
}
