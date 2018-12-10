package Test1;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class ServiceHello {
    public String getValue(String name){
        return "我是："+name;
    }
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/helloWorld", new ServiceHello());
        System.out.println("result success");
    }
}
