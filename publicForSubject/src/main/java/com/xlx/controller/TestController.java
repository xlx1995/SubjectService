package com.xlx.controller;




import com.alibaba.fastjson.JSON;
import com.xlx.db.entity.Person;
import com.xlx.db.entity.User;
import com.xlx.service.UserService;
import com.xlx.util.ReMessage;
import com.util.RestClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Auther: 徐林啸
 * @Date: 2019/6/1 20:06
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
@Api(value = "/user", tags = "User接口")
public class TestController {

    @Autowired
    private UserService us ;

    @RequestMapping(value = "/t1.do" , method = RequestMethod.GET)
    @ApiOperation(value="新增用户信息", httpMethod="GET", response=ReMessage.class)
    public Object test1(){

        Person person = new Person(13, "张三", "男");

        ReMessage reMessage = new ReMessage(true, person, "");

        return JSON.toJSON(reMessage);

    }


    @RequestMapping(value = "/getUser/{username}" , method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息", notes = "根据id获取用户信息", httpMethod = "GET", response = User.class)
    public User t(@PathVariable String username){
        User user = new User();
        user.setUser_name(username);
        User u = us.checkUser(user);
        return u;
    }

    @RequestMapping(value = "t3" , method = RequestMethod.GET)
    @ApiOperation(value = "测试jar包结构" ,notes = "demo1" ,httpMethod = "GET")
    public String t3(){

        log.info(RestClient.class.getName());

        return RestClient.t();
    }




}
