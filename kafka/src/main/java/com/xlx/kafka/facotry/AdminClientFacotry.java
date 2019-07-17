package com.xlx.kafka.facotry;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Properties;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/17 21:30
 * @Description:
 */
@Slf4j
public class AdminClientFacotry {

    private static String address;

    @Autowired
    public void setAddress(String address){
        this.address = address;
    }

    public static AdminClient getAdminClient(){
        AdminClient client = null;
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, address);
        try{
            client = AdminClient.create(properties);
        }catch (Throwable e){
            log.error("fail to create AdminClient ---" , e);
        }
        return client;
    }


}
