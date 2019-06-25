package com.util;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private Logger logger = Logger.getLogger(RedisUtil.class);

    private RedisTemplate<Serializable,Object> redisTemplate;

    //移除多个键值
    public void remove(String... keys){
        for (String key:keys) {
            remove(key);
        }
    }

    //移除缓存中对应的键值
    public void remove(String key){
        if(exists(key)){
            redisTemplate.delete(key);
        }
    }

    //判断缓存中是否有对应的键值
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    //取出对应的值
    public Object getValue(String key){
        Object result = null;
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        result=operations.get(key);
        return result;
    }

    //写入缓存
    public boolean set(String key,Object value,Long expireTime){
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
