package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.userServer.dao.entity.UserType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ckz
 * @since 2020-03-28
 */
public interface IUserTypeService extends IService<UserType> {
    GridResponse<UserType> get(GridRequest defaultIfNull);

    void post(UserType userType);

    void put(UserType userType);

    void delete(List<String> ids);
}
