package com.xlx.client;
import redis.clients.jedis.Jedis;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/14 22:41
 * @Description:
 */
public class Client {

    public static void main(String[] args) {


        Jedis jedis = new Jedis("192.168.159.128",6379);
        jedis.set("monkey", "wukong");
        System.out.println(jedis.get("monkey"));

    }

}
