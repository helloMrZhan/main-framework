###  Bean的依赖注入入门

①创建 UserService，UserService 内部在调用 UserDao的save() 方法

```java
public class UserServiceImpl implements UserService {
	@Override
	public void save() {
         ApplicationContext applicationContext = new 
                 ClassPathXmlApplicationContext("applicationContext.xml");       	          
         UserDao userDao = (UserDao) applicationContext.getBean("userDao");	
         userDao.save();
 	}
 }
```

②将 UserServiceImpl 的创建权交给 Spring

```xml
<bean id="userService" class="com.zjq.service.impl.UserServiceImpl"/>
```

③从 Spring 容器中获得 UserService 进行操作

```java
ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
UserService userService = (UserService) applicationContext.getBean("userService");
userService.save();
```

###  Bean的依赖注入概念

依赖注入（Dependency Injection）：它是 Spring 框架核心 IOC 的具体实现。

在编写程序时，通过控制反转，把对象的创建交给了 Spring，但是代码中不可能出现没有依赖的情况。

IOC 解耦只是降低他们的依赖关系，但不会消除。例如：业务层仍会调用持久层的方法。

那这种业务层和持久层的依赖关系，在使用 Spring 之后，就让 Spring 来维护了。

简单的说，就是坐等框架把持久层对象传入业务层，而不用我们自己去获取

###  Bean的依赖注入方式

①构造方法

​      创建有参构造

```java
public class UserServiceImpl implements UserService {
@Override
public void save() {
ApplicationContext applicationContext = new 
                 ClassPathXmlApplicationContext("applicationContext.xml"); 
    UserDao userDao = (UserDao) applicationContext.getBean("userDao");    
          userDao.save();
    }
 }
```

​      配置Spring容器调用有参构造时进行注入

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl"/>
<bean id="userService" class="com.zjq.service.impl.UserServiceImpl">      		   	
    <constructor-arg name="userDao" ref="userDao"></constructor-arg>
</bean>
```

②set方法

​      在UserServiceImpl中添加setUserDao方法

```java
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;  
        } 
    @Override    
    public void save() {      
   		 userDao.save();
	}
}
```

​      配置Spring容器调用set方法进行注入

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl"/>
<bean id="userService" class="com.zjq.service.impl.UserServiceImpl">
	<property name="userDao" ref="userDao"/>
</bean>
```

set方法:P命名空间注入

​      P命名空间注入本质也是set方法注入，但比起上述的set方法注入更加方便，主要体现在配置文件中，如下：

​      首先，需要引入P命名空间：

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

其次，需要修改注入方式

```xml
<bean id="userService" class="com.zjq.service.impl.UserServiceImpl" p:userDao-
 ref="userDao"/>
```

###     Bean的依赖注入的数据类型

上面的操作，都是注入的引用Bean，处了对象的引用可以注入，普通数据类型，集合等都可以在容器中进行注入。

注入数据的三种数据类型 

普通数据类型

引用数据类型

集合数据类型

其中引用数据类型，此处就不再赘述了，之前的操作都是对UserDao对象的引用进行注入的，下面将以set方法注入为例，演示普通数据类型和集合数据类型的注入。

**Bean的依赖注入的数据类型**

（1）普通数据类型的注入

```java
public class UserDaoImpl implements UserDao {
    private String company;
    private int age;
    public void setCompany(String company) {
        this.company = company;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void save() {
        System.out.println(company+"==="+age);
        System.out.println("UserDao save method running....");   
    }
}

```

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl">
    <property name="company" value="huawei"></property>
    <property name="age" value="15"></property>
</bean>
```

（2）集合数据类型（List<String>）的注入

```java
public class UserDaoImpl implements UserDao {
	private List<String> strList;
	public void setStrList(List<String> strList) {
		this.strList = strList;
	}
	public void save() {
        System.out.println(strList);
        System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl">
    <property name="strList">
        <list>
            <value>aaa</value>
            <value>bbb</value>
            <value>ccc</value>
        </list>
    </property>
</bean>
```

（3）集合数据类型（List<User>）的注入

```java
public class UserDaoImpl implements UserDao {
	private List<User> userList;
	public void setUserList(List<User> userList) {
	this.userList = userList;  
 }
public void save() {
	System.out.println(userList);
	System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="u1" class="com.zjq.domain.User"/>
<bean id="u2" class="com.zjq.domain.User"/>
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl">
    <property name="userList">
        <list>
            <bean class="com.zjq.domain.User"/>
            <bean class="com.zjq.domain.User"/>
            <ref bean="u1"/>
            <ref bean="u2"/>       
        </list>
    </property>
</bean>
```

（4）集合数据类型（ Map<String,User> ）的注入

```java
public class UserDaoImpl implements UserDao {
    private Map<String,User> userMap;
    public void setUserMap(Map<String, User> userMap) {
    this.userMap = userMap;
    }    
public void save() {      
	System.out.println(userMap);
	System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="u1" class="com.zjq.domain.User"/>
<bean id="u2" class="com.zjq.domain.User"/>
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl">
    <property name="userMap">
        <map>            
            <entry key="user1" value-ref="u1"/>
            <entry key="user2" value-ref="u2"/>
        </map>
    </property>
</bean>
```

（5）集合数据类型（Properties）的注入

```java
public class UserDaoImpl implements UserDao {
    private Properties properties;
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
	public void save() {
		System.out.println(properties);
		System.out.println("UserDao save method running....");
	}
}
```

```xml
<bean id="userDao" class="com.zjq.dao.impl.UserDaoImpl">
    <property name="properties">
        <props>
            <prop key="p1">aaa</prop>
            <prop key="p2">bbb</prop> 
            <prop key="p3">ccc</prop>
        </props>
    </property>
</bean>
```
