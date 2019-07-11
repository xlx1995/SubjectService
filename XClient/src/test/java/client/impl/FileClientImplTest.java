package client.impl;

import client.FileClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/11 22:50
 * @Description:
 */
public class FileClientImplTest {


    @Test
    public void t(){
        String url = "http://localhost:8080/server/file/upload";
        File file = new File("E:\\file\\test.txt");
        FileClient fileClient = new FileClientImpl();
        int i = fileClient.uploadFile(url, file);
        System.out.println(i);
    }

    @Test
    public void upload(){
        String url = "http://localhost:8080/server/file/upload";
        File file = new File("E:\\file\\test.txt");
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = null;
        try {
            //服务器地址
            HttpPost httpPost = new HttpPost(url);
            MultipartEntityBuilder mEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532 );
            mEntityBuilder.addBinaryBody("file", file);
            httpPost.setEntity(mEntityBuilder.build());
            response = httpclient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity resEntity = response.getEntity();
                result = EntityUtils.toString(resEntity);
                // 消耗掉response
                EntityUtils.consume(resEntity);
            }

    }catch (Throwable e){
        e.printStackTrace();}
    }

}