package com.zjq.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zjq
 * @date 2023/2/26 19:31
 * <p>title:</p>
 * <p>description:</p>
 */
public class UserFactoryBeanTest {

    /**
     * 获取FactoryBean产⽣的对象
     */
    @Test
    public void getFactoryBeanInstance(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object userBean = applicationContext.getBean("userBean");
        System.out.println("bean:" + userBean);
    }


    /**
     * 获取FactoryBean产⽣的对象
     */
    @Test
    public void getFactoryBean(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Object userBean = applicationContext.getBean("&userBean");
        System.out.println("bean:" + userBean);
    }
}
