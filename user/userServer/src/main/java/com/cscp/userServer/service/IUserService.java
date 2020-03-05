package com.cscp.userServer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponseWrapper;
import com.cscp.userServer.dao.entity.User;
import com.cscp.userServer.vo.UserVo;
import dto.UserDto;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author ckz
 * @since 2019-10-26
 */
public interface IUserService extends IService<User> {
    UserVo current();

    GridResponseWrapper get(GridRequest gridRequest);

    void post(UserDto userDto);

    void put(UserDto userDto);

    void delete(List<String> ids);
}
