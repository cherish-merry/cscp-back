package com.cscp.common.utils;

import lombok.Data;

/***
 * @discription 分页参数
 * @author: cscp
 * @date: 2019/8/20
 */

@Data
public class Page {
    private int current;
    private int size;
}