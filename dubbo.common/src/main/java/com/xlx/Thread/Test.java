package com.xlx.Thread;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/1 23:06
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
//        final Hero gareen = new Hero();
//        gareen.name = "盖伦";
//        gareen.hp = 616;
//        gareen.damage = 50;
//
//        final Hero teemo = new Hero();
//        teemo.name = "提莫";
//        teemo.hp = 300;
//        teemo.damage = 30;
//
//        Hero bh = new Hero();
//        bh.name = "赏金猎人";
//        bh.hp = 500;
//        bh.damage = 65;
//
//        Hero leesin = new Hero();
//        leesin.name = "盲僧";
//        leesin.hp = 455;
//        leesin.damage = 80;
//
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//
//                while(!teemo.isDead()){
//                    gareen.attackHero(teemo);
//                }
//            }
//        };
//        thread.start();

        Thread t1= new Thread(){
            @Override
            public void run(){
                int seconds =0;
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.printf("已经玩了LOL %d 秒%n", seconds++);
                }
            }
        };
        t1.start();


    }

    public void adugen() {
        while (true) {
            for (int i = 0; i < 3; i++) {
                System.out.printf("波动拳第%d发%n", i + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("开始为时5秒的充能");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
