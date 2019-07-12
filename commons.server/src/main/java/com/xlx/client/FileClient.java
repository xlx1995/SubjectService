package com.xlx.client;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/10 20:25
 * @Description:
 */
@Slf4j
public class FileClient {

    public void uploadFile(File file){

        if(!file.exists()){
            try {
                log.warn("please check file path " + file.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
