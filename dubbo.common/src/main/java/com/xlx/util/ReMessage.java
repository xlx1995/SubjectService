package com.xlx.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 21:10
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReMessage implements Serializable {

    private static final long serialVersionUID = -7102439230325746732L;
    private boolean success = true;

    private Object data;

    private String message = "ok";

    private Integer code;


}
