package TestHibernate;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.pojo.User;
import com.service.UserService;


public class TestHibernate {

    @Test
    public void test() {
        ClassPathXmlApplicationContext cx =new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml","spring-mvc.xml"});
        UserService userService=(UserService) cx.getBean("userService");
        User user=userService.selectById(1);
        System.out.println(JSON.toJSONString(user,true));
    }
}
