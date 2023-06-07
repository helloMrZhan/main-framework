package com.zjq.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * BeanFactoryPostProcessor 接⼝实现类
 * @author 共饮一杯无
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public MyBeanFactoryPostProcessor() {
        System.out.println("BeanFactoryPostProcessor的实现类构造函数...");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory
                                               beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor的实现⽅法调⽤中......");
    }
}