package com.cscp.userServer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cscp.userServer.dao.entity.RoleMenu;
import com.cscp.userServer.dao.mapper.RoleMenuMapper;
import com.cscp.userServer.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    /***
     * @discription 角色菜单绑定
     * @param: [roleId, menuIds]
     * @return: void
     * @author: ckz
     * @date: 2020/1/6
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public void roleMenuBind(String roleId, List<String> menuIds) {
        QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("r_id", roleId);
        List<RoleMenu> roleMenus = list(queryWrapper);
        List<String> ownedMenus = roleMenus.stream().map(RoleMenu::getMId).collect(Collectors.toList());
        List<String> toDelete;
        if (!CollectionUtils.isEmpty(menuIds)) {
            List<RoleMenu> toInsert = menuIds.stream().filter(s -> !ownedMenus.contains(s)).map(s -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRId(roleId);
                roleMenu.setMId(s);
                return roleMenu;
            }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(toInsert)) {
                saveBatch(toInsert);
            }
            toDelete = ownedMenus.stream().filter(s -> !menuIds.contains(s)).collect(Collectors.toList());
        } else {
            toDelete = ownedMenus;
        }
        if (!CollectionUtils.isEmpty(toDelete)) {
            QueryWrapper<RoleMenu> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("r_id", roleId).in("m_id", toDelete);
            remove(queryWrapper);
        }
    }
}
