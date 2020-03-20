package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.userServer.dao.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cscp.userServer.dao.entity.School;

import java.util.List;

/**
 * <p>
 * 专业 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
public interface IMajorService extends IService<Major> {

    GridResponse<Major> get(GridRequest gridRequest);

    void post(Major major);

    void put(Major major);

    void delete(List<String> ids);
}
