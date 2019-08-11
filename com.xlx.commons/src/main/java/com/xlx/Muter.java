package com.xlx;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/29 15:51
 * @Description:
 */
public class Muter {

    private final static Object MUTER = new Object();

    public void accessRessource()  {
        synchronized (MUTER){

            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        final Muter muter = new Muter();

        for(int i = 0 ; i < 5; i++){
            new Thread(muter::accessRessource).start();
        }

    }

}
