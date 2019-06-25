package com.Interceptor;

import com.util.RedisUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MethodCacheInterceptor implements MethodInterceptor {
    private Logger logger = Logger.getLogger(MethodInterceptor.class);
    private RedisUtil redisUtil;
    private List<String> targetNamesList;
    private List<String> methodNamesList;
    private Long defaultCacheExpireTime;

    public MethodCacheInterceptor() {
        try {
            File file = new File(this.getClass().getClassLoader().getResource("cacheConf.properties").getPath());
            InputStream in = new FileInputStream(file);
            Properties p = new Properties();
            p.load(in);
            String[] targetNames = p.getProperty("targetNames").split(",");
            String[] methodNames = p.getProperty("methodNames").split(",");
            defaultCacheExpireTime = Long.valueOf(p.getProperty("defaultCacheExpireTime"));
            targetNamesList = new ArrayList<String>(targetNames.length);
            methodNamesList = new ArrayList<String>(methodNames.length);
            int maxLen = targetNames.length > methodNames.length ? targetNames.length : methodNames.length;
            for (int i = 0; i < maxLen; i++) {
                if(i<targetNames.length){
                    targetNamesList.add(targetNames[i]);
                }
                if(i<methodNames.length){
                    methodNamesList.add(methodNames[i]);
                }
            }
            System.out.println("---------------------------->初始化methodInterceptor完成！");
        } catch (Exception e) {
            System.out.println("---------------------------------->文件读取失败");
            e.printStackTrace();
        }
    }

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object value = null ;
        String targetName = methodInvocation.getThis().getClass().getName();
        String methodName = methodInvocation.getMethod().getName();
        System.out.println("------------------------------>进入method拦截器");
        if(!isAddCache(targetName,methodName)){
            System.out.println("不缓存的方法");
            return methodInvocation.proceed();
        }
        Object[] arguments = methodInvocation.getArguments();
        String key = getCacheKey(targetName,methodName,arguments);
        try{
            if(redisUtil.exists(key)){
                System.out.println("从缓存中取得数据");
                return redisUtil.getValue(key);
            }
            value = methodInvocation.proceed();
            if(value != null){
                final String tkey = key;
                final Object tvalue = value;
                new Thread(new Runnable() {
                    public void run() {
                        redisUtil.set(tkey,tvalue,defaultCacheExpireTime);
                    }
                }).start();
            }
        }catch(Exception e){
            if (value == null)
            return methodInvocation.proceed();
        }
        return value;
    }

    private boolean isAddCache(String targetName,String methodName){
        if(targetNamesList.contains(targetName) || methodNamesList.contains(methodName)){
            return true;
        }
        return false;
    }

    private String getCacheKey(String targetName,String methodName,Object[] arguments){
        StringBuffer sb = new StringBuffer();
        sb.append(targetName+"_"+methodName);
        if(arguments!=null&&arguments.length!=0){
            for (int i = 0; i < arguments.length; i++) {
                sb.append("_").append(arguments[i]);
            }
        }
        return sb.toString();
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
