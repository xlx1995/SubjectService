package com.xlx.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:07
 * @Description:
 */
@Slf4j
public class IOUtils {



    public static void close(InputStream inputStream){
        try {
            if (inputStream != null){
                inputStream.close();
            }
        }catch (IOException e) {
           log.error("fail to close stream" + e);
        }
    }

    public static void close(OutputStream outputStream){
        try {
            if (outputStream != null){
                outputStream.close();
            }
        }catch (IOException e) {
            log.error("fail to close stream" + e);
        }
    }

}
