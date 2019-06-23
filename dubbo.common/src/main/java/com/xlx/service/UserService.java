package com.xlx.service;


import com.xlx.entity.User;
import com.xlx.util.ReMessage;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/22 19:39
 * @Description:
 */
public interface UserService {

    User checkUser(User user);

    ReMessage getAllUser();

    ReMessage query(User user);

    ReMessage delete(User user);

    ReMessage update(User user);

    ReMessage save(User user);

}
