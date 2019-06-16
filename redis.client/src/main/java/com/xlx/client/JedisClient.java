package com.xlx.client;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 16:47
 * @Description:
 */
public interface JedisClient {

    String set(String key, String value);
    String get(String key);
    Boolean exists(String key);
    Long expire(String key, int seconds);
    Long ttl(String key);
    Long incr(String key);
    Long hset(String key, String field, String value);
    String hget(String key, String field);
    Long hdel(String key, String... field);


}
