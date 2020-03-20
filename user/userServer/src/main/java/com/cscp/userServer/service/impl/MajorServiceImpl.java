package com.cscp.userServer.service.impl;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridService;
import com.cscp.userServer.dao.entity.Major;
import com.cscp.userServer.dao.mapper.MajorMapper;
import com.cscp.userServer.service.IMajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    MajorMapper majorMapper;

    @Override
    public GridResponse<Major> get(GridRequest gridRequest) {
        return new GridService<Major>().getGridResponse(majorMapper,gridRequest);
    }

    @Override
    public void post(Major major) {
        save(major);
    }

    @Override
    public void put(Major major) {
        updateById(major);
    }

    @Override
    public void delete(List<String> ids) {
        removeByIds(ids);
    }
}
