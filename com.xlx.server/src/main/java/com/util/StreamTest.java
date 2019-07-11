package com.util;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/25 22:46
 * @Description:
 */
public class StreamTest {


    public static void main(String[] args) {

        List<Integer> list  = Arrays.asList(55,33,22);
        long count = list.stream().filter(Integer->Integer>33).count();
        System.out.println(count);
    }
}
