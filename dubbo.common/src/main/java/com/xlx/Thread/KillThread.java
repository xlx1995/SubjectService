package com.xlx.Thread;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/1 23:05
 * @Description:
 */
public class KillThread extends Thread {

    private Hero h1;
    private Hero h2;

    public KillThread(Hero h1, Hero h2){
        this.h1 = h1;
        this.h2 = h2;
    }

    @Override
    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
