package client;

import java.io.File;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:23
 * @Description:
 */
public interface FileClient {

    int uploadFile(String url , File file);

    /**
     * 下载文件
     * @param url
     * @param localName
     * @param fileName
     */
    void download(String url , String localName , String fileName);

    String delete(String url ,String fileName);



}
