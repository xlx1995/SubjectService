package com.xlx.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 徐林啸
 * @Date: 2019/5/30 22:29
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReMessage {

    private Integer code = 0;

    private Boolean success = false;

    private String message ;

    private Object data;



}
