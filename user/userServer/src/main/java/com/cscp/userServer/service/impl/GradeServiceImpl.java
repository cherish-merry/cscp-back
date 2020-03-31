package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridService;
import com.cscp.common.utils.ViewException;
import com.cscp.userServer.dao.entity.Grade;
import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.dao.mapper.GradeMapper;
import com.cscp.userServer.service.IGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 年级 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements IGradeService {
    @Autowired
    GradeMapper gradeMapper;

    @Override
    public GridResponse<Grade> get(GridRequest gridRequest) {
        return new GridService<Grade>().getGridResponse(gradeMapper, gridRequest);
    }

    @Override
    public void post(Grade grade) {
        if(grade.getName()==null){
            throw  new ViewException("grade name should not be null");
        }
        if(CollectionUtils.isEmpty(gradeMapper.selectList(new QueryWrapper<Grade>().eq("name",grade.getName())))){
            throw new ViewException("this grade already exist");
        }
        save(grade);
    }

    @Override
    public void put(Grade grade) {
        updateById(grade);
    }

    @Override
    public void delete(List<String> ids) {
        removeByIds(ids);
    }
}
