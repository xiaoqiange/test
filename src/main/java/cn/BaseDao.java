package cn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class BaseDao {
    private static Logger log=Logger.getLogger(BaseDao.class);
    public static void main(String[] args) {
        try {
            InetAddress address=InetAddress.getLocalHost();
            String ip =address.getHostAddress();
            log.info(ip);
        } catch (UnknownHostException e1) {
            System.out.println("获取本机地址失败！");
            e1.printStackTrace();
        }
        
//        try {
//            Process process = Runtime.getRuntime().exec("ping 192.168.13.142");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = null;
//            StringBuffer sb = new StringBuffer();
//            while ((line = bufferedReader.readLine()) != null) {
//                {
//                    if (line.length() != 0) {
//                        sb.append(line + "\r\n");
//                    }
//                }
//            }
//            System.out.println(new String(new String(sb).getBytes(), "GBK"));
//        } catch (IOException e) {
//            System.out.println("windows命令执行错误！");
//        }
    }

}

class Box<T> {
    private T data;

    public Box() {

    }

    public Box(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
