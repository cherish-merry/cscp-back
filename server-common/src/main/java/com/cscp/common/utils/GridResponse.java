package com.cscp.common.utils;

import lombok.Data;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/2 - 14:55
 */
@Data
public class GridResponse<T> {
    private long total;
    private List<T> record;
}
