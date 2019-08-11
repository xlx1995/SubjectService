package com.xlx;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/24 22:45
 * @Description:
 */
public class TestMain {

    public static void main(String[] args) {

        final TicketWindow ticketWindow = new TicketWindow();
        Thread t1 = new Thread(ticketWindow,"x号窗口");
        Thread t2 = new Thread(ticketWindow,"二号窗口");
        Thread t3 = new Thread(ticketWindow,"三号窗口");
        Thread t4 = new Thread(ticketWindow,"四号窗口");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }




}
