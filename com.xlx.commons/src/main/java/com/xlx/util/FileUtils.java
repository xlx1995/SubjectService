package com.xlx.util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

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

    /**
     *
     * 根据文件路径获得byte数组
     * @param filePath
     * @return
     */
    public static byte[] getByte(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 根据字节数组获取文件
     * @param bfile
     * @param filePath
     * @param fileName
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

}
