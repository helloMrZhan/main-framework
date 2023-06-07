package com.zjq.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * Bean⽣命周期关键时机点
 * @author 共饮一杯无
 */
public class MyBean implements InitializingBean {

    /**
     * 构造函数
     */
    public MyBean(){
        System.out.println("MyBean 构造器...");
    }

    /**
     * InitializingBean 接⼝实现
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MyBean afterPropertiesSet...");
    }
}
