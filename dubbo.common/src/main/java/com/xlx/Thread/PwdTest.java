package com.xlx.Thread;

import java.util.Random;

/**
 * @Auther: 徐林啸
 * @Date: 2019/7/2 22:46
 * @Description:
 */
public class PwdTest {

    /**
     * 1. 生成一个长度是3的随机字符串，把这个字符串当作 密码
     * <p>
     * 2. 创建一个破解线程，使用穷举法，匹配这个密码
     * <p>
     * 3. 创建一个日志线程，打印都用过哪些字符串去匹配，这个日志线程设计为守护线程
     * <p>
     * 提示： 破解线程把穷举法生成的可能密码放在一个容器中，日志线程不断的从这个容器中拿出可能密码，并打印出来。 如果发现容器是空的，就休息1秒，如果发现不是空的，就不停的取出，并打印。
     */

    public static String pwdCreate(int pwd_len) {
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
       // 生成随机数，取绝对值，防止生成负数，
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();

    }

    public static void main(String[] args) {

        System.out.println(pwdCreate(3));
    }


}
