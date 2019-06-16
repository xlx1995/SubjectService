package com.xlx.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 徐林啸
 * @Date: 2019/6/5 21:10
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReMessage {

    private boolean success = true;

    private Object data;

    private String message = "ok";


}
