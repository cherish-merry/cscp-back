package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.Role;
import com.cscp.userServer.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author ckz
 * @since 2019-11-02
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    @PostMapping("/getGridRoles")
    public Result getGridRoles(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iRoleService.getGridRoles(gridRequest));
    }

    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        iRoleService.addRole(role);
        return ResultUtil.success();
    }

    @PostMapping("/updateRole")
    public Result updateRole(@RequestBody Role role) {
        iRoleService.updateById(role);
        return ResultUtil.success();
    }

    @PostMapping("/deleteRole")
    public Result deleteRole(@RequestParam List<String> roleIds) {
        iRoleService.deleteRole(roleIds);
        return ResultUtil.success();
    }

    @GetMapping("/getRolesByUserId")
    public Result getRolesByUserId(String userId) {
        return ResultUtil.success(iRoleService.getRolesByUserId(userId));
    }
}
