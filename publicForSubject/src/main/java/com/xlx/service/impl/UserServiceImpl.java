package com.xlx.service.impl;

import com.xlx.common.cache.UserCache;
import com.xlx.db.entity.User;
import com.xlx.db.mapper.UserMapper;
import com.xlx.service.UserService;
import com.xlx.util.ReMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 22:52
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
