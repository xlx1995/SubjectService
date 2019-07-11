package client.impl;

import client.FileClient;
import com.xlx.util.HttpClientUtil;

import java.io.File;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:26
 * @Description:
 */
public class FileClientImpl implements FileClient {
    @Override
    public int uploadFile(String url, File file) {
        return HttpClientUtil.saveFile(url, file);
    }
}
