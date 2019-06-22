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
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/getUser/{username}" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息", notes = "根据id获取用户信息", httpMethod = "GET", response = User.class)
    public User t(@PathVariable String username){
        User user = new User();
        user.setUser_name(username);
        User u = userService.checkUser(user);
        return u;
    }

    @RequestMapping(value = "user" ,method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息", notes = "加载所有用户信息", httpMethod = "GET", response = User.class)
    public ReMessage getAllUser(){

        return userService.getAllUser();

    }

}


