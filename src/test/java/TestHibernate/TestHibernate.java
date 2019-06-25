package TestHibernate;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.email.EmailUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    @Test
    public void Pattern() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m=p.matcher("aaa2223bb"); 
        m.find();//匹配2223 
        int start = m.start();//返回3 
        System.out.println(start);
        System.out.println(m.end());//m.group();   //返回2223bb返回7,返回的是2223后的索引号 
//        m.group();//返回2223 

        Matcher m2=p.matcher("2223bb"); 
        m2.lookingAt();   //匹配2223 
        m2.start();   //返回0,由于lookingAt()只能匹配前面的字符串,所以当使用lookingAt()匹配时,start()方法总是返回0 
        m2.end();   //返回4 
        m2.group();   //返回2223 

        Matcher m3=p.matcher("2223bb"); 
        m3.matches();   //匹配整个字符串 
        m3.start();   //返回0,原因相信大家也清楚了 
        m3.end();   //返回6,原因相信大家也清楚了,因为matches()需要匹配所有字符串 
    }
    @Test
    public void Pattern3() {
        String rs = "CTCM8618928726320,8618928726320,CTCT18928726320";
        String[] rsArry=rs.split(",");
        for (String str : rsArry) {
//            Pattern pattern = Pattern.compile("(CTC[T|M])(86)(1[3|4|5|6|7|8]\\d{9})");
//            Matcher matcher = pattern.matcher(str);
//            if(matcher.find()){
//                do{
//                    String group = matcher.group().trim();
//                    rs+=group.substring(0, 4);
//                    System.out.println(rs);
//                }while(matcher.find());
//            }
            System.out.println(subPhone(str));
        }
//        System.out.println(Pattern.compile("(\\d{3})\\d{4}(\\d+)").matcher(rs).replaceAll("$1****$2"));
    }
    public String subPhone(String phone){
        String str = "";
        if(phone.length()==17 || phone.length()==13){
            str = Pattern.compile("(\\d{5})\\d{4}(\\d+)").matcher(phone).replaceAll("$1****$2");
        }else{
            str = Pattern.compile("(\\d{3})\\d{4}(\\d+)").matcher(phone).replaceAll("$1****$2");
        }
        return str;
    }
    @Test
    public void Pattern2() {
//        Pattern p = Pattern.compile("([A-Z]+)(\\d+)");
        Pattern p = Pattern.compile("(1[3|4|5|6|7|8]\\d{9})|(CTC[T|M]+)(86){0,1}(1[3|4|5|6|7|8]\\d{9})");
//        Matcher m=p.matcher("CTCT1587655794318988916664,CTCT8615876557943,CTCM8615504531949T CAN/CAN/T02062672183/18988916664/GUANGZHOU YIDA TRADING C;T CAN/O. LTD. /WANGYIZHUO"); 
        Matcher m=p.matcher("CTCM18928726320,18928726320,CTCT18928726320"); 
        if(m.find()){
            do{
                System.out.println(m.group()+",");;
            }while(m.find());
        }
            
//        System.out.println(m.group(1));
//        int start = m.start(1);
//        System.out.println(start);
//        int end = m.end(1);
//        System.out.println(end);
//        int start2 = m.start(2);
//        System.out.println(start2);
//        int end2 = m.end(2);
//        System.out.println(end2);
//        String group1 = m.group(1);
//        String group2 = m.group(2);
//        System.out.println(m.group());
//        m.find();
//        int groupCount1 = m.groupCount();
//        System.out.println(groupCount1);
//        System.out.println(m.group(1));
//        System.out.println(m.group(2));
//        System.out.println(m.group(3));
//        System.out.println(m.group());
    }
    @Test
    public void TestURL() throws IOException {
        JSONObject jsonObject = null;
        //设置请求路径
        String path = "http://127.0.0.1:8080/Test/user/userlist";
        //创建url实例
        URL url = new URL(path);
        //建立连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //如果有返回值并且需要获取的话就打开
        conn.setDoInput(true);
        //如果请求路径需要参数的话，就需要我们使用输出流传参
        conn.setDoOutput(true);
        //是否使用缓存
        conn.setUseCaches(false);
        //请求方式
        conn.setRequestMethod("POST");
        //获取输入流
        InputStream inputStream = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream,"utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuffer result = new StringBuffer();
        String str = null;
        //接收输入流中的字符串
        while((str=br.readLine())!=null){
            result.append(str);
        }
        br.close();
        isr.close();
        // 释放资源
        inputStream.close();
        inputStream = null;
        //关闭连接
        conn.disconnect();
        jsonObject = JSONObject.parseObject(result.toString());
        System.out.println(JSON.toJSONString(jsonObject.get("rows"), true));
    }
    
    @Test  
    public void sendEmail() throws Exception {  
        String userName = "694289326@qq.com"; // 发件人邮箱  
        String password = "fblgamhgvmpbbcfj"; // 发件人密码  (授权码)
        String smtpHost = "smtp.qq.com"; // 邮件服务器  
  
        String to = "xiaoqiangimi@sina.com"; // 收件人，多个收件人以半角逗号分隔  
        String subject = "这是邮件的主题"; // 主题  
        String body = "这是邮件的正文"; // 正文，可以用html格式的哟  
        List<String> attachments = Arrays.asList("D:\\uploadTest\\tupian.png"); // 附件的路径，多个附件也不怕  
  
        EmailUtil email = EmailUtil.entity(smtpHost, userName, password, to, null, subject, body, attachments);  
  
        email.send(); // 发送！  
    }  
    @Test
    public void t1() throws ClassNotFoundException{
        Class clazz = Class.forName("cn.email.EmailUtil");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
    @Test
    public void t2(){
        List<String> ll = new ArrayList<String>();
        ll.add("CTCT123123");
        ll.add("CTCM2222222");
        ll.add("CTCM2222222");
        ll.add("CTCT12312");
        ll.add("33333");
        Collections.sort(ll, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -(o1.compareToIgnoreCase(o2));
            }
        });
        System.out.println(ll);
    }
    @Test
    public void t3(){
        String str = "a1b2c3,";
        System.out.println(str.endsWith(",")?str.substring(0, str.length()-1):str);
    }
}
