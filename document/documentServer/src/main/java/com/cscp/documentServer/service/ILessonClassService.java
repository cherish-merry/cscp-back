package com.cscp.documentServer.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cscp.common.utils.GridResponse;
import com.cscp.documentServer.dao.entity.LessonClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ckz
 * @since 2020-02-08
 */
public interface ILessonClassService extends IService<LessonClass> {

    public GridResponse getLessonClassByUser(IPage page, String userId);

}
