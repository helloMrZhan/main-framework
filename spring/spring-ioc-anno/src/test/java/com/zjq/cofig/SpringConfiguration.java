package com.zjq.cofig;

import org.springframework.context.annotation.*;

//标志该类是Spring的核心配置类
@Configuration
//<context:component-scan base-package="com.zjq"/>
@ComponentScan("com.zjq")
//<import resource=""/>
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {

}
