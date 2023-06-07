package com.zjq.test;

import com.zjq.bean.MyBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * IOC分析案例
 *
 * @author 共饮一杯无
 */
public class SpringIOCTest {

    /**
     * Ioc 容器源码分析基础案例
     */
    @Test
    public void testIoC() {
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        MyBean myBean = applicationContext.getBean(MyBean.class);
        System.out.println(myBean);
    }




}
