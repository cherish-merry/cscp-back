package com.cscp.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    public static GridResponse getResponseByPage(IPage iPage){
        GridResponse gridResponse = new GridResponse();
        gridResponse.setTotal(iPage.getTotal());
        gridResponse.setRecord(iPage.getRecords());
        return gridResponse;
    }
}
