package com.xlx;


import java.util.stream.IntStream;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/25 22:46
 * @Description:
 */
public class TicketWindow implements Runnable {

    private final static int maxNum = 50;

    private int index = 1;

    private String name ;

    private final static Object MUTER = new Object();



    @Override
    public void run() {
        synchronized (MUTER){
            while(index<=maxNum){
                try {
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread()+":的号码:"+(index++));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {



    }


}
