package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.userServer.service.IRoleMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("角色-菜单绑定")
    @PutMapping("/bind")
    public Result bind(String roleId, @RequestParam(required = false) List<String> menuIds) {
        iRoleMenuService.roleMenuBind(roleId, menuIds);
        return ResultUtil.success();
    }
}
