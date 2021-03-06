package com.xlx.common.cache;



import com.xlx.entity.User;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 22:07
 * @Description:
 */
public interface UserCache {

    List<User> load();

    User load(User user);

    void save(List<User> users);

    void save(User users);



}
