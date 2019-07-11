package com.xlx.util;

import lombok.extern.slf4j.Slf4j;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:10
 * @Description:
 */
@Slf4j
public class FileUtils {


    /**
     * 向指定路径写文件
     * @param inputStream
     * @param filePath
     */
    public static void write(InputStream inputStream,String filePath){
        OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(filePath);
            byte[] bytes = new byte[1024];
            int len = -1 ;
            while( -1 != (len = inputStream.read(bytes))){
                outputStream.write(bytes, 0, len);
            }
        }catch (Throwable e){
            log.error("fail to write file" + e);
        }finally {
            IOUtils.close(inputStream);
            IOUtils.close(outputStream);
        }

    }

}
