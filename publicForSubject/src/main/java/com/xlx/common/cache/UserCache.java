package com.xlx.common.cache;

import com.xlx.db.entity.User;

import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/16 22:07
 * @Description:
 */
public interface UserCache {

    List<User> load();

    void save(List<User> users);

}
