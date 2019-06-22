package com.xlx.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xlx.common.cache.UserCache;

import com.xlx.db.mapper.UserMapper;
import com.xlx.entity.User;
import com.xlx.service.UserService;
import com.xlx.util.ReMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/22 19:41
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper um ;

    @Autowired
    private UserCache userCache;

    @Override
    public User checkUser(User user) {

        return um.checkUser(user.getUser_name());

    }

    @Override
    public ReMessage getAllUser() {

        ReMessage reMessage = new ReMessage();

        List<User> load = userCache.load();
        if(load!=null && load.size()!=0){
            log.info("从缓存中取数据");
            reMessage.setData(load);
            return  reMessage;
        }else{
            log.info("从数据库中取数据");
            load = um.getAllUser();
            reMessage.setData(load);
            userCache.save(load);
            return  reMessage;
        }

    }
}
