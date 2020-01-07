package com.cscp.common.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/1/2 - 15:02
 */
public class GridService<T> {
    public GridResponse<T> getGridResponse(BaseMapper<T> baseMapper, GridRequest gridRequest) {
        GridResponse<T> gridResponse = new GridResponse<>();
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        if (gridRequest.getFilterParams() != null) {
            gridRequest.getFilterParams().forEach(queryWrapper::eq);
        }
        if (gridRequest.getSearchParams() != null) {
            queryWrapper.and(i -> {
                i.or();
                gridRequest.getSearchParams().forEach((k, v) -> {
                    i.or().like(k, v);
                });
                return i;
            });
        }
        if (gridRequest.getPage() != null) {
            IPage<T> iPage = baseMapper.selectPage(new Page<T>(gridRequest.getPage().getCurrent(), gridRequest.getPage().getSize()), queryWrapper);
            gridResponse.setRecord(iPage.getRecords());
            gridResponse.setTotal(iPage.getTotal());
        } else {
            List<T> records = baseMapper.selectList(queryWrapper);
            gridResponse.setRecord(records);
            gridResponse.setTotal(records.size());
        }
        return gridResponse;
    }
}
