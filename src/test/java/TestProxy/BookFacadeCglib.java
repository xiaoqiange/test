package TestProxy;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

public class BookFacadeCglib implements MethodInterceptor {

    private Object obj;

    public Object getInstance(Object target) {
        this.obj = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object arg0, Method arg1, Object[] args, MethodProxy arg3) throws Throwable {
        System.out.println("预处理——————");
        arg3.invokeSuper(arg0, args);
        System.out.println("调用后操作——————");
        return "try";
    }
}
