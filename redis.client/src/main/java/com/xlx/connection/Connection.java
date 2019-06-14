package com.xlx.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/14 23:07
 * @Description: 连接redis
 */
public class Connection {

    private String ip;

    private String port;

    private Socket socket;

    private OutputStream outputStream;

    private InputStream inputStream;

    public Connection(String ip, String port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect(){

        try {
            socket = new Socket(ip,Integer.parseInt(port));

            inputStream = socket.getInputStream();

            outputStream = socket.getOutputStream();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
