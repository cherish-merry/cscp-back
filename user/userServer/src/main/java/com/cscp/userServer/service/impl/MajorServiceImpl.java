package com.cscp.userServer.service.impl;

import com.cscp.userServer.dao.entity.Major;
import com.cscp.userServer.dao.mapper.MajorMapper;
import com.cscp.userServer.service.IMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 专业 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {

}
