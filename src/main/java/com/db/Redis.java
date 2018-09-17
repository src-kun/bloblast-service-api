package com.db;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Redis {
	
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	/** 
     * 压栈 
     *  
     * @param key 
     * @param value 
     * @return 
     */  
    public Long push(String key, Object value) {  
        return redisTemplate.opsForList().leftPush(key, value);  
    }  
  
    /** 
     * 出栈 
     *  
     * @param key 
     * @return 
     */  
    public String pop(String key) {  
        return (String) redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 入队 
     *  
     * @param key 
     * @param value 
     * @return 
     */  
    public Long in(String key, String value) {  
        return redisTemplate.opsForList().rightPush(key, value);  
    }  
  
    /** 
     * 出队 
     *  
     * @param key 
     * @return 
     */  
    public String out(String key) {  
        return (String) redisTemplate.opsForList().leftPop(key);  
    }  
  
    /** 
     * 栈/队列长 
     *  
     * @param key 
     * @return 
     */  
    public Long length(String key) {  
        return redisTemplate.opsForList().size(key);  
    }  
  
    /** 
     * 范围检索 
     *  
     * @param key 
     * @param start 
     * @param end 
     * @return 
     */  
    public List<Object> range(String key, int start, int end) {  
        return redisTemplate.opsForList().range(key, start, end);  
    }  
  
    /** 
     * 移除 
     *  
     * @param key 
     * @param i 
     * @param value 
     */  
    public void remove(String key, long i, String value) {  
        redisTemplate.opsForList().remove(key, i, value);  
    }  
  
    /** 
     * 检索 
     *  
     * @param key 
     * @param index 
     * @return 
     */  
    public Object index(String key, long index) {  
        return redisTemplate.opsForList().index(key, index);  
    }  
  
    public void set(String key, Object value) {  
    	redisTemplate.opsForValue().set(key, value); 
    } 
    
    public Object get(String key) {  
    	return redisTemplate.opsForValue().get(key); 
    }  
  
    /** 
     * 裁剪 
     *  
     * @param key 
     * @param start 
     * @param end 
     */  
    public void trim(String key, long start, int end) {  
        redisTemplate.opsForList().trim(key, start, end);  
    }  
}
