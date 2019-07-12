package client.impl;

import client.FileClient;
import com.xlx.util.HttpClientUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:26
 * @Description:
 */
public class FileClientimpl implements FileClient {
    @Override
    public int uploadFile(String url, File file) {
        return HttpClientUtil.saveFile(url, file);
    }

    @Override
    public void download(String url, String localName, String fileName) {
        HttpClientUtil.download(fileName,localName,url);
    }

    @Override
    public String delete(String url, String fileName) {
        Map<String,String> param = new HashMap<>();
        param.put("fileName",fileName);
        return HttpClientUtil.sendDelete(url,param);
    }
}
