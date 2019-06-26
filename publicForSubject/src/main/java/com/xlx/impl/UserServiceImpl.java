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
        List<User> load = null;
        try{
            load = userCache.load();
        }catch (Throwable e){
            e.printStackTrace();
            log.error("redis 连接错误");
        }
        if(load!=null && load.size()!=0){
            log.info("从缓存中取数据");
            reMessage.setData(load);
            return  reMessage;
        }else{
            log.info("从数据库中取数据");
            load = um.query();
            reMessage.setData(load);
            userCache.save(load);
            return  reMessage;
        }

    }

    @Override
    public ReMessage query(User user) {
        ReMessage reMessage = new ReMessage(false, null, "");
        User user_bak  = null;
        try{
            user_bak = userCache.load(user);

        }catch (Throwable e){
            e.printStackTrace();
            log.error("redis --错误");
        }
        if(user_bak!=null){
            log.info("从缓存中读取数据");
            reMessage.setData(user_bak);
            reMessage.setSuccess(true);
        }else {
            log.info("从数据库中读取数据");
            user_bak = um.query(user);
            userCache.save(user_bak);
            reMessage.setData(user_bak);
            reMessage.setSuccess(true);
        }
        return reMessage;
    }

    @Override
    public ReMessage delete(User user) {
        ReMessage reMessage = new ReMessage(false, null, "");
        Integer delete = 0;
        try{
            delete = um.delete(user);
        }catch (Throwable e){
            e.printStackTrace();
            log.error("数据库错误");
        }
        if(delete >0){
            reMessage.setSuccess(true);
            reMessage.setMessage("删除成功");
        }else{
            reMessage.setSuccess(false);
            reMessage.setMessage("删除失败");
        }
        return reMessage;
    }

    @Override
    public ReMessage update(User user) {
        ReMessage reMessage = new ReMessage(false, null, "");
        Integer update = 0;
        try{
            update = um.update(user);
        }catch (Throwable e){
            e.printStackTrace();
            log.error("数据库错误");
        }
        if(update >0){
            reMessage.setSuccess(true);
            reMessage.setMessage("更新成功");
        }else{
            reMessage.setSuccess(false);
            reMessage.setMessage("更新失败");
        }
        return reMessage;
    }

    @Override
    public ReMessage save(User user) {
        ReMessage reMessage = new ReMessage(false, null, "");
        Integer save = 0;
        try{
            save = um.save(user);
        }catch (Throwable e){
            e.printStackTrace();
            log.error("数据库错误");
        }
        if(save >0){
            reMessage.setSuccess(true);
            reMessage.setMessage("保存成功");
        }else{
            reMessage.setSuccess(false);
            reMessage.setMessage("保存失败");
        }
        return reMessage;
    }
}
