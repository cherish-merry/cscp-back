package com.cscp.userServer.service;

import com.cscp.userServer.dao.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
public interface IMenuService extends IService<Menu> {
    List<Menu> getMenusByUsername(String username);

    List<Menu> getAllMenus();
}
