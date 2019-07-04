package com.xlx.common.cache.impl;

import com.alibaba.fastjson.JSON;
import com.xlx.client.JedisClient;
import com.xlx.common.cache.UserCache;

import com.xlx.entity.User;
import com.xlx.kafka.client.KafkaConsumerClient;
import com.xlx.kafka.client.KafkaProducerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 22:11
 * @Description:
 */
@Repository
public class UserCacheImpl implements UserCache {

    @Autowired
    private JedisClient jedisClient;

    @Override
    public List<User> load() {
        String userList = jedisClient.get("UserList");
        List<User> users = JSON.parseArray(userList, User.class);
        return users;
    }

    @Override
    public User load(User user) {
        String s = jedisClient.get(user.getUser_id());
        User users_ = JSON.parseObject(s, User.class);
        return users_;
    }

    @Override
    public void save(List<User> users) {
        jedisClient.set("UserList", JSON.toJSONString(users));
    }

    @Override
    public void save(User user) {
        jedisClient.set(user.getUser_id() , JSON.toJSONString(user));
    }

}
