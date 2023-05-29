package com.zjq.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor实现类
 * @author 共饮一杯无
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    public MyBeanPostProcessor() {
        System.out.println("BeanPostProcessor 实现类构造函数...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if ("myBean".equals(beanName)) {
            System.out.println("BeanPostProcessor 实现类 postProcessBeforeInitialization ⽅法被调⽤中......");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        if ("myBean".equals(beanName)) {
            System.out.println("BeanPostProcessor 实现类 postProcessAfterInitialization ⽅法被调⽤中......");
        }
        return bean;
    }
}