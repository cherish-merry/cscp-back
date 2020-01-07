package com.cscp.userServer.service.impl;

import com.cscp.userServer.dao.entity.School;
import com.cscp.userServer.dao.mapper.SchoolMapper;
import com.cscp.userServer.service.ISchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学校 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {

}
