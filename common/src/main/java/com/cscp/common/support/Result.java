package com.cscp.common.support;

import lombok.Data;

/**
 * @author chen kezhuo
 * @discription
 * @date 2019/11/1 - 23:24
 */
@Data
public class Result {
    private static final long serialVersionUID = 1L;
    private int code;
    private String msg;
    private Object data;
}
