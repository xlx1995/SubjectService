package com.xlx.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 16:56
 * @Description:
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:application.xml")
public class JedisClientUtilTest {

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void set() {

        JedisClientUtil jedisClientUtil = new JedisClientUtil();
        jedisClientUtil.set("abc", "abc");
        System.out.println(jedisClientUtil.get("abc"));


    }

    @Test
    public void get() {
    }
}