package com.kfwy.hkp.common.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.hash.DecoratingStringHashMapper;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;
import java.util.concurrent.Callable;

/**
 * 基於redis的springcache注解缓存
 */
@Component
public class RedisCache implements Cache{
    Logger logger = LoggerFactory.getLogger(RedisCache.class);
    @Autowired
    private StringRedisTemplate redisTemplate;
    private String name;
    protected ObjectMapper objectMapper=new ObjectMapper();
    protected HashMapper<Object, String, String> mapper;
    public RedisCache() {
        this.mapper = new DecoratingStringHashMapper(new Jackson2HashMapper(true));
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public void clear() {
        logger.debug("-------缓存清理------");
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    @Override
    public void evict(Object key) {
        logger.debug("-------缓存刪除------");
        final String keyf=key.toString();
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(keyf.getBytes()));

    }

    @Override
    public ValueWrapper get(Object key) {
        logger.debug("------缓存获取-------"+key.toString());
        final String keyf = key.toString();
        Object object = null;
       object =  redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key1 = keyf.getBytes();
            byte[] value = connection.get(key1);
            if (value == null) {
                logger.debug("------缓存不存在-------");
                return null;
            }
            return SerializationUtils.deserialize(value);
        });
        ValueWrapper obj=(object != null ? new SimpleValueWrapper(object) : null);
        logger.debug("------获取到内容-------"+obj);
        return obj;
    }

    @Override
    public void put(Object key, Object value) {
        logger.debug("-------加入缓存------");
        logger.debug("key----:"+key);
        logger.debug("key----:"+value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
      /*  try {
            saveEntity(key.toString(),valuef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keyb = keyString.getBytes();
            byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
            connection.set(keyb, valueb);
            if (liveTime > 0) {
                connection.expire(keyb, liveTime);
            }
            return 1L;
        });

    }

    @Override
    public <T> T get(Object arg0, Class<T> arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void saveEntity(String key, Object t) throws JsonProcessingException {
        final long liveTime = 86400;
        String result = objectMapper.writeValueAsString(t);
        /*redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] keyb = key.getBytes();
            byte[] valueb = SerializationUtils.serialize((Serializable) result);
            connection.set(keyb, valueb);
            if (liveTime > 0) {
                connection.expire(keyb, liveTime);
            }
            return 1L;
        });*/
        redisTemplate.opsForValue().set(key,result);
    }

    public Object parseEntity(String key) {
        String result = redisTemplate.opsForValue().get(key);
        if (result!=null && result!=""){
           return JSON.parse(result);
        }else{
            return null;
        }
    }


}
