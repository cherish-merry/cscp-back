package com.cscp.documentServer.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cscp.common.utils.GridResponse;
import com.cscp.documentServer.dao.entity.LessonClass;
import com.cscp.documentServer.dao.mapper.LessonClassMapper;
import com.cscp.documentServer.service.ILessonClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-02-08
 */
@Service
public class LessonClassServiceImpl extends ServiceImpl<LessonClassMapper, LessonClass> implements ILessonClassService {

    @Resource
    LessonClassMapper lessonClassMapper;

    @Override
    public GridResponse getLessonClassByUser(IPage page, String userId) {
        IPage<LessonClass> lessonClasses = lessonClassMapper.getLessonClassByUser(page, userId);
        GridResponse response=new GridResponse<>();
        response.setRecords(lessonClasses.getRecords());
        response.setTotal(lessonClasses.getTotal());
        return response;
    }
}
