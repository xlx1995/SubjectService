package com.xlx.controller;

import com.xlx.client.JedisClient;
import com.xlx.client.JedisClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 17:22
 * @Description:
 */
@RestController
@RequestMapping(value = "/test")
public class RedisController {

    @Autowired
    private JedisClient jedisClient;

    @RequestMapping(value = "t1")
    public String test1(){
        jedisClient.set("123", "123");
        return jedisClient.get("123");
    }

}
