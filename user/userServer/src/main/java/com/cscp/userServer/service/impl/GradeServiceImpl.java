package com.cscp.userServer.service.impl;

import com.cscp.userServer.dao.entity.Grade;
import com.cscp.userServer.dao.mapper.GradeMapper;
import com.cscp.userServer.service.IGradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
