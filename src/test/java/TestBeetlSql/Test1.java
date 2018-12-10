package TestBeetlSql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.ConnectionSource;
import org.beetl.sql.core.ConnectionSourceHelper;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLLoader;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.pojo.User;

public class Test1 {
    private static Properties properties = new Properties();

    private static InputStream in = null;

    private static String driver = null;

    private static String url = null;

    private static String userName = null;

    private static String password = null;

    private static ConnectionSource source = null;

    private static DBStyle style = null;

    private static SQLLoader loader = null;

    private static UnderlinedNameConversion nc = null;

    private static SQLManager sqlManager = null;

    User user = new User();

    private static void loaderSqlManager() {
        in = Test1.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            properties.load(in);
            driver = properties.getProperty("jdbc.DriverClassName");
            url = properties.getProperty("jdbc.url");
            userName = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            source = ConnectionSourceHelper.getSimple(driver, url, userName, password);
            style = new MySqlStyle();
            loader = new ClasspathLoader("/config");
            nc = new UnderlinedNameConversion();
            sqlManager = new SQLManager(style, loader, source, nc, new Interceptor[] { new DebugInterceptor() });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    @Test
//    public void save() {
//        loaderSqlManager();
//        user.setName("ÀîËÄ");
//        user.setPassword("123456");
//        user.setBirthday(new Date());
//        user.setCode("u1002");
//        user.setPhone("18744213467");
//        user.setSex(1);
//        sqlManager.insert(user);
//    }

    @Test
    public void selectById() {
        loaderSqlManager();
        int id = 1;
        user = sqlManager.unique(User.class, id);
        System.out.println(JSON.toJSONString(user, true));
    }
    
    @Test
    public void selectAll(){
        loaderSqlManager();
        List<User> list=sqlManager.template(user);
        System.out.println(JSON.toJSONString(list,true));
    }
    @Test
    public void delete(){
        
    }
}
