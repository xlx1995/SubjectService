package com.xlx.service.impl;

import com.xlx.db.entity.User;
import com.xlx.db.mapper.UserMapper;
import com.xlx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 22:52
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper um ;

    @Override
    public User checkUser(User user) {

        return um.checkUser(user.getUser_name());

    }


}
