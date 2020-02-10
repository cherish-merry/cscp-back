package com.cscp.documentServer.service.impl;

import com.cscp.documentServer.dao.entity.Homework;
import com.cscp.documentServer.dao.mapper.HomeworkMapper;
import com.cscp.documentServer.service.IHomeworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-02-08
 */
@Service
public class HomeworkServiceImpl extends ServiceImpl<HomeworkMapper, Homework> implements IHomeworkService {

}
