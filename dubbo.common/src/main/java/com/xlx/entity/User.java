package com.xlx.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 22:36
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 3664748393106326790L;

    private String user_id ;

    private String user_name ;

    private String user_password ;

}
