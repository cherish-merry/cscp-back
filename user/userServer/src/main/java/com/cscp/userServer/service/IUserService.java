package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridResponseWrapper;
import com.cscp.userServer.dao.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import dto.UserDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    UserDto current();

    GridResponseWrapper get(GridRequest gridRequest);

    void post(UserDto userDto);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    void put(UserDto userDto);

    void delete(List<String> ids);
}
