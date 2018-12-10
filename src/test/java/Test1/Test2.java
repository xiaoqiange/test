package Test1;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.util.JdbcUtil;

public class Test2 {
    @Test
    public void test1(){
        String sql="select * from user";
        JdbcUtil.getConnection(sql);
    }
    
    @Test
    public void test2(){
        UUID uuid=new UUID(2317213, 42432);
        System.out.println(uuid);
    }
    
    @Test
    public void test3() throws ParseException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(new Date());
        Date date1=sdf.parse("2018-08-12");
        System.out.println(date1);
    }
    @Test
    public void test4(){
        List list=new ArrayList();
        System.out.println(list);
    }
    @Test
    public void test5(){
        float a=2.11f;
        float b=2.11f;
        int c=2;
        System.out.println(a==b);
        System.out.println(a==c);
    }
    @Test
    public void test6(){
        float a=2.11f;
        float b=2.11f;
        int c=2;
        System.out.println(a==b);
        System.out.println(a==c);
    }
    @Test
    public void TestSocket() throws UnknownHostException, IOException{
        String str = new String("Welcome-to-Runoob");
        
        System.out.println("- 分隔符返回值 :" );
        for (String retval: str.split("-")){
            System.out.print(retval);
        }
    }
    
}
