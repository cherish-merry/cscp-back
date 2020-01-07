package com.cscp.userServer.service;

import com.cscp.userServer.dao.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    void roleMenuBind(String roleId, List<String> menuIds);
}
