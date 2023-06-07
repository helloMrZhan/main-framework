package com.zjq.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置类
 * @author 共饮一杯无
 */
@Configuration
@ComponentScan("com.zjq")
//开启spring对注解AOP的⽀持
@EnableAspectJAutoProxy
public class SpringConfiguration {
}