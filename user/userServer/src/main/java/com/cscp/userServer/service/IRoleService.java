package com.cscp.userServer.service;

import com.cscp.common.utils.GridRequest;
import com.cscp.common.utils.GridResponse;
import com.cscp.common.utils.GridResponseWrapper;
import com.cscp.userServer.dao.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface IRoleService extends IService<Role> {

    GridResponse<Role> getGridRoles(GridRequest gridRequest);

    void deleteRole(List<String> roleIds);

    void addRole(Role role);

    GridResponse<Role> getRolesByUserId(String userId);
}
