package com.xlx.controller;

import com.xlx.entity.User;
import com.xlx.service.UserService;
import com.xlx.util.ReMessage;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/22 20:29
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/getUser/{username}" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息", notes = "根据id获取用户信息", httpMethod = "GET", response = ReMessage.class)
    public User t(@PathVariable String username){
        User user = new User();
        user.setUser_name(username);
        User u = userService.checkUser(user);
        return u;
    }

    @RequestMapping(value = "/user" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", notes = "加载所有用户信息", httpMethod = "GET", response = ReMessage.class)
    public ReMessage getAllUser(){

        return userService.getAllUser();

    }


    @RequestMapping(value = "/user/{id}" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息", notes = "获取用户信息", httpMethod = "GET", response = ReMessage.class)
    public ReMessage query(@PathVariable("id") String id){
        User user = new User();
        user.setUser_id(Integer.parseInt(id));
        return userService.query(user);
    }

    @RequestMapping(value = "/user/{id}" ,method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除信息", notes = "删除用户", httpMethod = "DELETE", response = ReMessage.class)
    public ReMessage delete(@PathVariable("id") String id){
        User user = new User();
        user.setUser_id(Integer.parseInt(id));
        return userService.delete(user);
    }

    @RequestMapping(value = "/user" ,method = RequestMethod.PUT)
    @ApiOperation(value = "添加", notes = "添加用户", httpMethod = "PUT", response = ReMessage.class)
    public ReMessage save(User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user" ,method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改用户信息", httpMethod = "POST", response = ReMessage.class)
    public ReMessage uodate(User user){
        return userService.update(user);
    }

}


