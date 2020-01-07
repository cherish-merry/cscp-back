package com.cscp.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @author chen kezhuo
 * @discription 分页+过滤
 * @date 2019/8/20 - 20:01
 */
@Data
public class GridRequest implements Serializable {
    private Page page;
    private Map<String, Object> filterParams;
    private Map<String, Object> searchParams;
}
