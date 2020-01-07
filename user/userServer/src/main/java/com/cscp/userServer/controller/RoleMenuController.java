package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.userServer.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色菜单关联表 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@RestController
@RequestMapping("/role-menu")
public class RoleMenuController {
    @Autowired
    IRoleMenuService iRoleMenuService;

    @PostMapping("/roleMenuBind")
    public Result roleMenuBind(String roleId, @RequestParam(required = false) List<String> menuIds) {
        iRoleMenuService.roleMenuBind(roleId,menuIds);
        return ResultUtil.success();
    }
}