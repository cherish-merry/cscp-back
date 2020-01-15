package com.cscp.userServer.controller;


import com.cscp.common.support.Result;
import com.cscp.common.support.ResultUtil;
import com.cscp.common.utils.GridRequest;
import com.cscp.userServer.dao.entity.Role;
import com.cscp.userServer.service.IRoleService;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang.ObjectUtils;
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
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    @GetMapping("/")
    public Result get(@RequestBody(required = false) GridRequest gridRequest) {
        return ResultUtil.success(iRoleService.getGridRoles((GridRequest) ObjectUtils.defaultIfNull(gridRequest, new GridRequest())));
    }

    @PostMapping("/")
    public Result post(@RequestBody Role role) {
        iRoleService.addRole(role);
        return ResultUtil.success();
    }

    @PutMapping("/")
    public Result put(@RequestBody Role role) {
        iRoleService.updateById(role);
        return ResultUtil.success();
    }

    @DeleteMapping("/")
    public Result delete(@RequestParam List<String> roleIds) {
        iRoleService.deleteRole(roleIds);
        return ResultUtil.success();
    }


    @GetMapping("/{userId}")
    public Result getByUserId(@PathVariable("userId") String userId) {
        return ResultUtil.success(iRoleService.getRolesByUserId(userId));
    }
}
