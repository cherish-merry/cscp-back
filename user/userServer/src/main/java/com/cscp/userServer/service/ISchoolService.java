package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cscp.userServer.dao.entity.School;
import dto.UserDto;

import java.util.List;

/**
 * <p>
 * 学校 服务类
 * </p>
 *
 * @author ckz
 * @since 2020-01-02
 */
public interface ISchoolService extends IService<com.cscp.userServer.dao.entity.School> {
    GridResponse<School> get(GridRequest gridRequest);

    void post(School school);

    void put(School school);

    void delete(List<String> ids);
}
