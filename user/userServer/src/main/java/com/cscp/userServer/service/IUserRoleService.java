package com.cscp.userServer.service;

import com.cscp.userServer.dao.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户-角色 服务类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface IUserRoleService extends IService<UserRole> {

    void bindUserRole(String userId, List<String> roleIds);
}
