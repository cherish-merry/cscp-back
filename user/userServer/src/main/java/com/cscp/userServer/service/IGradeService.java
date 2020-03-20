package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.userServer.dao.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 年级 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
public interface IGradeService extends IService<Grade> {

    GridResponse<Grade> get(GridRequest gridRequest);

    void post(Grade grade);

    void put(Grade grade);

    void delete(List<String> ids);
}
