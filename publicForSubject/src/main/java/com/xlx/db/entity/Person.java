package com.xlx.db.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 21:09
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private static final long serialVersionUID = 3007933840342001327L;
    private Integer age;

    private String name;

    private String sex;


}
