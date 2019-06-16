package com.xlx.client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/14 22:41
 * @Description:
 */
public class Client {

    public static void main(String[] args) {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(100);
        jedisPoolConfig.setMaxWaitMillis(1000);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.159.128",6379,10000);

        Jedis jedis = jedisPool.getResource();

        jedis.set("monkey", "xlx");

        System.out.println(jedis.get("monkey"));
        jedis.close();

    }

}
