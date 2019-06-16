package com.xlx.service;

import com.xlx.db.entity.User;
import com.xlx.util.ReMessage;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 22:51
 * @Description:
 */
public interface UserService {

    User checkUser(User user);

    ReMessage getAllUser();

}
