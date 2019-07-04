package com.xlx.Thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/1 23:02
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hero {

    public String name;
    public float hp;
    public int damage;

    public void attackHero(Hero h) {
        try {
            //为了表示攻击需要时间，每次攻击暂停1000毫秒
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);

        if(h.isDead()){System.out.println(h.name +"死了！");}

    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }

}
