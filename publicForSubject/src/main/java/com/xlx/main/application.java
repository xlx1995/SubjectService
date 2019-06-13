package com.xlx.main;

import com.xlx.util.CIPHelper;
import com.xlx.util.ReMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/9 21:23
 * @Description:
 */

@RestController
@RequestMapping(value = "application")
@Api(value = "/application", tags = "Application接口")
public class application {

    @Autowired
    private CIPHelper cipHelper;

    @RequestMapping(value = "t1.do" , method = RequestMethod.GET)
    @ApiOperation(value="测试bean注入", httpMethod="GET", response= String.class)
    public String t1(){

        return cipHelper.getUrl();

    }


}
