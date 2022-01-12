##  spring概述

###  Spring是什么

Spring是分层的 Java SE/EE应用 full-stack 轻量级开源框架，以 [IoC（Inverse Of Control：反转控制）](spring-ioc)和 [AOP（Aspect Oriented Programming：面向切面编程）](spring-aop)为内核。

提供了展现层 SpringMVC和持久层 Spring JDBCTemplate以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的Java EE 企业应用开源框架


###  Spring的优势

方便解耦，简化开发

AOP 编程的支持

声明式事务的支持

方便程序的测试

###  Spring的体系结构

![](img\图片29.png)



##  spring快速入门

###  Spring程序开发步骤 

①导入 Spring 开发的基本包坐标

②编写 Dao 接口和实现类

③创建 Spring 核心配置文件

④在 Spring 配置文件中配置 UserDaoImpl

⑤使用 Spring 的 API 获得 Bean 实例

###  导入Spring开发的基本包坐标

```xml
<properties>
	<spring.version>5.0.5.RELEASE</spring.version>
</properties>
<!--导入spring的context坐标，context依赖core、beans、expression-->
<dependencies> 
    <dependency>  
        <groupId>org.springframework</groupId> 
        <artifactId>spring-context</artifactId> 
        <version>${spring.version}</version>
    </dependency>
</dependencies>

```

###  编写Dao接口和实现类

```java
public interface UserDao {  
    public void save();
}

public class UserDaoImpl implements UserDao {  
        @Override  
        public void save() {
        	System.out.println("UserDao save method running....");
	}
}
```

###  创建Spring核心配置文件

在类路径下（resources）创建applicationContext.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"             		   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
    http://www.springframework.org/schema/beans      	             http://www.springframework.org/schema/beans/spring-beans.xsd">
</beans>
```

###  在Spring配置文件中配置UserDaoImpl

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"             		   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="
    http://www.springframework.org/schema/beans      	             http://www.springframework.org/schema/beans/spring-beans.xsd">
   <bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl"></bean>
</beans>

```

###  使用Spring的API获得Bean实例

```java
public class SpringTest {
    @Test
    public void test1(){
            ApplicationContext applicationContext = new  
                 ClassPathXmlApplicationContext("applicationContext.xml");
                 UserDao userDao = (UserDao) applicationContext.getBean("userDao");   				 
                 userDao.save();
     }
 }
```


##  Spring配置文件

###  Bean标签基本配置 

用于配置对象交由Spring 来创建。

默认情况下它调用的是类中的无参构造函数，如果没有无参构造函数则不能创建成功。

基本属性：

id：Bean实例在Spring容器中的唯一标识

class：Bean的全限定名称

###  Bean标签范围配置 

scope:指对象的作用范围，取值如下： 

| 取值范围         | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| singleton        | 默认值，单例的                                               |
| prototype        | 多例的                                                       |
| request          | WEB   项目中，Spring   创建一个   Bean   的对象，将对象存入到   request   域中 |
| session          | WEB   项目中，Spring   创建一个   Bean   的对象，将对象存入到   session   域中 |
| global   session | WEB   项目中，应用在   Portlet   环境，如果没有   Portlet   环境那么globalSession   相当于   session |

1）当scope的取值为singleton时

​      Bean的实例化个数：1个

​      Bean的实例化时机：当Spring核心文件被加载时，实例化配置的Bean实例

​      Bean的生命周期：

对象创建：当应用加载，创建容器时，对象就被创建了

对象运行：只要容器在，对象一直活着

对象销毁：当应用卸载，销毁容器时，对象就被销毁了

2）当scope的取值为prototype时

​      Bean的实例化个数：多个

​      Bean的实例化时机：当调用getBean()方法时实例化Bean

对象创建：当使用对象时，创建新的对象实例

对象运行：只要对象在使用中，就一直活着

对象销毁：当对象长时间不用时，被 Java 的垃圾回收器回收了

###  Bean生命周期配置

init-method：指定类中的初始化方法名称

destroy-method：指定类中销毁方法名称

###  Bean实例化三种方式

1） 使用无参构造方法实例化

​      它会根据默认无参构造方法来创建类对象，如果bean中没有默认无参构造函数，将会创建失败

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl"/>
```

2） 工厂静态方法实例化

​      工厂的静态方法返回Bean实例

```java
public class StaticFactoryBean {
    public static UserDao createUserDao(){    
    return new UserDaoImpl();
    }
}
```

```xml
<bean id="userDao" class="com.zjq.factory.StaticFactoryBean" 
      factory-method="createUserDao" />
```

3） 工厂实例方法实例化

​      工厂的非静态方法返回Bean实例

```java
public class DynamicFactoryBean {  
	public UserDao createUserDao(){        
		return new UserDaoImpl(); 
	}
}
```

```xml
<bean id="factoryBean" class="com.zjq.factory.DynamicFactoryBean"/>
<bean id="userDao" factory-bean="factoryBean" factory-method="createUserDao"/>
```

###  引入其他配置文件（分模块开发）

实际开发中，Spring的配置内容非常多，这就导致Spring配置很繁杂且体积很大，所以，可以将部分配置拆解到其他配置文件中，而在Spring主配置文件通过import标签进行加载

```xml
<import resource="applicationContext-xxx.xml"/>
```

##  spring相关API

###  ApplicationContext的继承体系

applicationContext：接口类型，代表应用上下文，可以通过其实例获得 Spring 容器中的 Bean 对象

###  ApplicationContext的实现类

1）ClassPathXmlApplicationContext 

​      它是从类的根路径下加载配置文件 推荐使用这种

2）FileSystemXmlApplicationContext 

​      它是从磁盘路径上加载配置文件，配置文件可以在磁盘的任意位置。

3）AnnotationConfigApplicationContext

​      当使用注解配置容器对象时，需要使用此类来创建 spring 容器。它用来读取注解。

###  getBean()方法使用

```java
public Object getBean(String name) throws BeansException {  
	assertBeanFactoryActive();   
	return getBeanFactory().getBean(name);
}
public <T> T getBean(Class<T> requiredType) throws BeansException {   			    	
    assertBeanFactoryActive();
	return getBeanFactory().getBean(requiredType);
}
```

其中，当参数的数据类型是字符串时，表示根据Bean的id从容器中获得Bean实例，返回是Object，需要强转。

当参数的数据类型是Class类型时，表示根据类型从容器中匹配Bean实例，当容器中相同类型的Bean有多个时，则此方法会报错

**getBean()方法使用**

```java
ApplicationContext applicationContext = new 
            ClassPathXmlApplicationContext("applicationContext.xml");
  UserService userService1 = (UserService) applicationContext.getBean("userService");
  UserService userService2 = applicationContext.getBean(UserService.class);
```
