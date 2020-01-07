package com.cscp.userServer.service.impl;

import com.cscp.userServer.dao.entity.Menu;
import com.cscp.userServer.dao.mapper.MenuMapper;
import com.cscp.userServer.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getMenusByUsername(String username) {
        return buildMenus(menuMapper.getMenuByUserName(username));
    }

    @Override
    public List<Menu> getAllMenus() {
        return buildMenus(list());
    }

    private List<Menu> buildMenus(List<Menu> menus) {
        LinkedList<Menu> root = new LinkedList<>();
        for (Menu menu : menus) {
            if (menu.getParent() == null || StringUtils.isEmpty(menu.getParent())) {
                root.add(menu);
            }
        }
        Collections.sort(root);
        for (Menu menu : root) {
            findChildren(menu, menus);
        }
        return root;
    }

    private void findChildren(Menu parent, List<Menu> allMenu) {
        for (Menu menu : allMenu) {
            if (parent.getId().equalsIgnoreCase(menu.getParent())) {
                parent.getChildren().add(menu);
                findChildren(menu, allMenu);
            }
        }
        Collections.sort(parent.getChildren());
    }
}
