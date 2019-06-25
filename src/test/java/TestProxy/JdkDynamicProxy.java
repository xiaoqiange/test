package TestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkDynamicProxy implements InvocationHandler {
    private Object obj;
    
    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
                .getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object obj1, Method method, Object[] args) throws Throwable {
        System.out.println("before"+obj1.getClass().getName());
        Object result = method.invoke(obj, args);
        System.out.println("after");
        return result;
    }

}
