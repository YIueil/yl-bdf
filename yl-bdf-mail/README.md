# yl-bdf-mail
## 1 基础使用
### 1.1 非Spring项目应用
在classpath下的properties目录添加mail.properties
```properties
  mail.smtp.host=smtp.163.com
  mail.smtp.port=465
  mail.smtp.auth=true
  mail.username=
  mail.password=
  mail.smtp.ssl.enable=true
  mail.smtp.starttls.enable=false
  mail.debug=true
```

获取服务
```java
  Session session = new MailSessionFactory().getSessionByProperties();
  mailService = new MailService();
  mailService.setSession(session);
```

发送邮件
```java
  List<String> toList = new ArrayList<>();
  List<File> fileList = new ArrayList<>();
  toList.add("yiueil@163.com");
  toList.add("511210125@qq.com");
  fileList.add(new File("C:\\Users\\Administrator\\Desktop\\123.txt"));
  fileList.add(new File("C:\\Users\\Administrator\\Desktop\\123 - 副本.txt"));
  mailService.send("这是一个群发测试邮件👻", "<h1>这是一个群发测试邮件👻</h1>", true, toList, fileList);
```

### 1.2 Spring项目应用
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--factory对象, 拥有两种创建session的方式: 通过已注入的属性 | 通过properties目录下的mail.properties配置文件-->
    <bean id="mailSessionFactory" class="cc.yiueil.factory.MailSessionFactory"/>

    <!--mailSession: 这里通过properties目录下的mail.properties配置文件创建-->
    <bean id="mailSession" factory-bean="mailSessionFactory" factory-method="getSessionByProperties"/>

    <!--mailService 实际邮件服务-->
    <bean class="cc.yiueil.service.MailService">
        <property name="session" ref="mailSession"/>
    </bean>
</beans>
```

```java
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/mail")
public class TestController implements LoggedController {
    @Autowired
    MailService mailService;

    @GetMapping(value="test/mail")
    public String test1(HttpServletRequest request){
        try {
            mailService.create()
                    .addTo("511210125@qq.com")
                    .setSubject("无题")
                    .setBody("也是无题")
                    .send();
        } catch (MessagingException e) {
            e.printStackTrace();
            return fail();
        }
        return success();
    }
}
```