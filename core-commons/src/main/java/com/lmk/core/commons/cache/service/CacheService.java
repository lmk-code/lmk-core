package com.lmk.core.commons.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis缓存服务
 */
public class CacheService {
    /**
     * 缓存服务
     */
    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 设置缓存，默认30分钟有效期
     * @param key
     * @param value
     */
    public void set(String key, Object value){
        set(key, value, 30, TimeUnit.MINUTES);
    }

    /**
     * 设置缓存
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void set(String key, Object value, long time, TimeUnit unit){
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 判断是否存在缓存Key
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 刷新缓存
     * @param key
     * @param time
     * @param unit
     */
    public void refresh(String key, long time, TimeUnit unit){
        redisTemplate.expire(key, time, unit);
    }

    /**
     * 删除缓存
     * @param key
     */
    public void delete(String key){
        redisTemplate.delete(key);
    }

    /**
     * 清空缓存
     */
    public void clean(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
    }

}
